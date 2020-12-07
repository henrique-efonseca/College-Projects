#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdbool.h> 
#include "Appointment.h"

Appointment appointment;
Appointment list_appointments[10];

int type_appointments[4];
int rooms[10];
int room;
int index_rooms;
int vacancies;


// S1
void initialize()
{
    for (int i = 0; i < sizeof(list_appointments) / sizeof(appointment); i++)
    {
        list_appointments[i].type = -1;
        rooms[i] = -1;
    }

    vacancies = sizeof(list_appointments) / sizeof(appointment);
}


// S2
void registPID()
{
    FILE *file;
    file = fopen("Server.pid", "w");
    fprintf(file, "PID do Servidor: %d", getpid());
    fclose(file);

    printf("\n");
    printf("\n");
    printf("------------------ <Cliniq-IUL Server> ---------------\n");
    printf("\n");
    printf("\n");
    printf("./Server started successfully with the following PID '%d'\n", getpid());
    printf("\n");
    printf("\n");
    printf("\n");  
}


// S3
void initiateAppointmentProcess()
{
    // S3.1
    if (access("AppointmentRequest.txt", F_OK) == 0){

    FILE *file = fopen("AppointmentRequest.txt", "r");
    char line_buffer[256];
    int i = 0;

        while (fgets(line_buffer, sizeof(line_buffer), file))
        {
        
       

            if (i == 0)
            {
                char aux[256];
                strcpy(aux, line_buffer + 15);
                appointment.type = atoi(aux);
            }

            if (i == 1)
            {
                strcpy(appointment.description, line_buffer + 22);

                for (int i = 0; i != '\n'; i++)
                {
                    if (appointment.description[i] == '\n') { appointment.description[i] = '\0'; }
                }
            }

            if (i == 2)
            {
                char aux[256];
                strcpy(aux, line_buffer + 14);
                appointment.pid_appointment = atoi(aux);
            }

            i++;
        }
    }

    else {
        printf("Error opening file! \n");
        return;
    }

    // S3.2)
    printf("<A new appointment request has arrived with the type '%d', description '%s' and PID '%d'>", appointment.type, appointment.description, appointment.pid_appointment);
    printf("\n\n");

    // S3.3
    room = 0;

    for (int i = 0; i < sizeof(list_appointments) / sizeof(appointment); i++)
    {
        if (list_appointments[i].type == -1)
        {
            room = i;
            break;
        }
    }

    if (vacancies <= 0)
    {
        printf("The list of appointments is full.\n");
        kill(appointment.pid_appointment, SIGUSR2);
        type_appointments[0]++;
    }

    // S3.4
    else if (vacancies > 0)
    {
        list_appointments[room] = appointment;
        printf("-> Appointment scheduled for the room '%d' \n\n", room);
        vacancies--;
        
        rooms[index_rooms] = room;
        room++;
        index_rooms++;

        switch (appointment.type)
        {
        case 1:
            type_appointments[1]++;
            break;

        case 2:
             type_appointments[2]++;
            break;
        case 3:
            type_appointments[3]++;
            break;
        }

        int pid;
        pid = fork();

        if (-1 == pid)
        {
            printf("Error. Please reinitiate the server.\n");
            exit(1);
        }
        if (pid == 0)
        {       
            
            printf("<Appointment Ongoing> \n");
            printf("\n");

            int current_room = room - 1;

            void ignoreSignals(){ 
                // Termina o processo 
                exit(0);
            }
           
            signal(SIGINT,ignoreSignals);
    

            // S3.4.1
            kill(appointment.pid_appointment, SIGHUP);

            // S3.4.2
            sleep(10);

            printf("<Appointment concluded in room '%d'>\n", current_room);
            printf("\n");
            printf("\n");

            kill(appointment.pid_appointment, SIGTERM); 

            // Sends the SIGUSR2 signal the the father process to update the arrays 'list_appointments' e 'rooms'
            kill(getppid(), SIGUSR2);
      
            exit(0);
           
        }
        else
        {
            // Waits for the arrival of new appointment requests
        }
    }
}


void updateListarooms()
{
    // pop()
    for (int i = 0; i < sizeof(rooms)/sizeof(int); i++)
    {
        rooms[i] = rooms[i + 1];
    }
     rooms[sizeof(rooms)/sizeof(int) - 1] = -1;
}


void updatelista()
{
int aux;

 for (int i = 0; i < sizeof(rooms)/sizeof(int); i++) 
 {
    if(rooms[i]!= -1){
        aux = rooms[i];
        break;    
    }       
}

    Appointment clear;
    list_appointments[aux] = clear;
    list_appointments[aux].type = -1;

    updateListarooms();
    room--;
    index_rooms--;
    vacancies++;
}


// S4
void serverShutDown()
{
   int buffer[4];
   FILE *file;

    if (access("StatsAppointments.dat", F_OK) == 0)
    {
    file = fopen("StatsAppointments.dat", "r");
    fread(&buffer, sizeof(buffer),1,file);
    fclose(file);
         
     type_appointments[0] += buffer[0];
     type_appointments[1] += buffer[1];
     type_appointments[2] += buffer[2];
     type_appointments[3] += buffer[3];
    }

    file = fopen("StatsAppointments.dat", "w");
    fwrite(type_appointments, sizeof(type_appointments),1,file);
    fclose(file);
    
    printf("\n");
    printf("\nServer shut down by administrator\n");
    printf("\n");
    printf("\n");
    printf("                   <All-Time Appointments>                \n");
    printf(" ---------------------------------------------------------\n");
    printf("| Missed          Normal           COVID19         Urgent |\n");
    printf("----------------------------------------------------------\n");
    printf("| %d               %d                %d               %d      |\n",type_appointments[0],type_appointments[1],type_appointments[2],type_appointments[3]);
    printf(" ---------------------------------------------------------\n");
    printf("\n");
    printf("\n");

    for(int i = 0; i < sizeof(list_appointments) / sizeof(appointment); i++)
    {
        if (list_appointments[i].type != -1)
        {
            kill(list_appointments[i].pid_appointment, SIGQUIT);
        }
    }

    remove("Server.pid");

    exit(0);
}

            
int main()
{
    signal(SIGINT, serverShutDown);  // S4

    initialize();  // S1
    registPID();   // S2

    signal(SIGUSR1, initiateAppointmentProcess); // S3  

    /* Receives the signal SIGUSR2 from child process when the appointments end
    and updates the arrays 'list_appointments' and 'rooms' */
    signal(SIGUSR2, updatelista);

        while (true)
        {
            pause();
        }
        return 0;
 }


