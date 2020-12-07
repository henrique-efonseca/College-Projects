#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <stdbool.h>
#include "Appointment.h" 

Appointment appointment;

int Server_PID;
bool appointmentHasInitiated;

// C1)
void getAppointmentInformation()
{
    printf("\n");
    printf("Welcome to Clinic-IUL!\n");
    printf("\n");
    printf("-------------- <MEDICAL APPOINTMENTS> -----------\n");
    printf("|                                               |\n");
    printf("| 1. Normal                                     |\n");
    printf("| 2. COVID19                                    |\n");
    printf("| 3. Urgent                                     |\n");
    printf("|                                               |\n");
    printf("-------------------------------------------------\n");
    printf("\n");

    printf("Please select a type of medical appointment: \n");

    scanf("%d", &appointment.type);

    if (appointment.type != 3 && appointment.type != 2 && appointment.type != 1)
    {
        while (appointment.type != 3 && appointment.type != 2 && appointment.type != 1)
        {
            printf("Invalid type of medical appointment. Please choose a valid type of medical appointment [1-3]: \n");
            scanf(" %d[^\n]", &appointment.type);
        }
    }

    printf("Please insert a description for the medical appointment: \n");
    scanf(" %[^\n]", &appointment.description);
}

// C2
void createAppointment()
{
    appointment.pid_appointment = getpid();
    char descriptive[25];

    switch (appointment.type)
    {
    case 1:
        strcpy(descriptive, "Normal");
        break;

    case 2:
        strcpy(descriptive, "COVID19");
        break;
    case 3:
        strcpy(descriptive, "Urgent");
        break;
    }

    printf("\n");
    printf("\n");
    printf("\n");
    printf("--------------- APPOINTMENT INFORMATION ------------\n");
    printf("\n");
    printf("Type --> %d_%s                                     \n", appointment.type, descriptive);
    printf("Description --> %s                                   \n", appointment.description);
    printf("PID --> %d                                           \n", appointment.pid_appointment);
    printf("\n");
    printf("-----------------------------------------------------\n");
    printf("\n");
    printf("\n");

    FILE *f;
    f = fopen("AppointmentRequest.txt", "w");

    fprintf(f, "Tipo Consulta: %d\nDescrição consulta: %s\nPID consulta: %d", appointment.type, appointment.description, appointment.pid_appointment);

    fclose(f);
}

// C3
void sendBeginAppointmentRequest()
{
    if (access("Server.pid", F_OK) != 0)
    {
        printf("Error! opening file\n");
        printf("\n");
        exit(1);
    }

    else
    {
        FILE *file = fopen("Server.pid", "r");
        char line_buffer[256];
        int i = 0;

        fgets(line_buffer, sizeof(line_buffer), file);

        strcpy(line_buffer, line_buffer + 17);
        Server_PID = atoi(line_buffer);

        kill(Server_PID, SIGUSR1);
    }
}

// C4
void appointmentInitiated()
{
    printf("Medical appointment initiated for the PID: '%d'\n", appointment.pid_appointment);
    remove("AppointmentRequest.txt");
    appointmentHasInitiated = true;

    void ignoreSIGINT()
    {
        /* Doesn't allow the appoitment to be canceled while it's ongoing */
    }

    signal(SIGINT, ignoreSIGINT);
}

// C5
void appointmentConluded()
{
    if (appointmentHasInitiated == true)
    {
        printf("Medical appointment concluded for the PID: '%d'\n", appointment.pid_appointment);
        printf("\n");
        exit(0);
    }
    else if (appointmentHasInitiated == false)
    {
        printf("Error. Medical appointment not initiated.\n");
        exit(1);
    }
}

// C6
void appointmentInvalid()
{
    printf("Medical appointment is not possible for the PID: %d\n", appointment.pid_appointment);
    remove("AppointmentRequest.txt");
    exit(0);
}

// C7
void appointmentCanceled()
{
    printf("\n\nPatient canceled medical appointment.\n\n");
    remove("AppointmentRequest.txt");
    exit(0);
}

/* If the server is shut down while this process is active
this process receives the SIGQUIT signal and it also shuts down */
void serverShutDown()
{
    printf("\nError. Server shut down.\n\n");
    exit(1);
}

int main()
{

    signal(SIGQUIT, serverShutDown); // Handles server shut down

    signal(SIGINT, appointmentCanceled); // C7

    getAppointmentInformation();   // C1)
    createAppointment();           // C2)
    sendBeginAppointmentRequest(); // C3)

    signal(SIGHUP, appointmentInitiated); // C4)
    signal(SIGTERM, appointmentConluded); // C5)
    signal(SIGUSR2, appointmentInvalid);  // C6)

    while (true)
    {
        // Passive waiting
        pause();
    }
}

