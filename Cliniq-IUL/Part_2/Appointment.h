#include <stdio.h>

typedef struct {
    int type; // Appointment Type: 1-Normal, 2-COVID19, 3-Urgent
    char description[100]; // Appointment Description
    int pid_appointment; // PID
    } Appointment;

