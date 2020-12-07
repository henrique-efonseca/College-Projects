#!/bin/bash

while true; do

echo
echo
echo
echo "Welcome to Clinic-IUL!"
echo
echo "----------MENU-----------"
echo "|                       |"
echo "| 1. Create Patients    |"
echo "| 2. Create Doctors     |"
echo "| 3. Stats              |"
echo "| 4. Evaluate Doctors   |"
echo "| 0. Exit               |"
echo "|                       |"
echo "-------------------------"
echo 
read -r -p "Enter your choice: " option
echo


while [[ ! "$option" =~ ^([0-4]|4)$  ]]; do

    read -r -p "Invalid option. Please enter a valid option [0-4]: " option
        
    if [[ "$option" =~ ^([0-4]|4)$ ]]; then 

        echo

    fi

done

 case $option in
 
 1)
 echo -e "You chose Create Patients. \n"
 ./cria_pacientes.sh
 echo -e "Here are the Patients: \n"

 cat pacientes.txt
 ;;

 2)
 echo -e "You chose Create a Doctor. \n"
 read -r -p "Please enter Doctor's Name: " a
 read -r -p "Please enter Doctor's Professional Card Number: " b
 read -r -p "Please enter Doctor's Speciality: " c 
 read -r -p "Please enter Doctor's E-mail: " d
 echo

 ./cria_medico.sh "$a" $b "$c" $d 
 ;;

 3)
 echo -e "You chose Stats. \n"
 read -r -p "Please enter a location to see Patients from that location:  " a
 read -r -p "Please enter a balance to see Doctors with a greater balance than the one provided: " b 
 echo

 ./stats.sh $a $b 
 ;;

 4)
 echo -e "You chose Evaluate Doctors. \n"

 ./avalia_medicos.sh
 ;;

 0)
 echo -e "You chose to exit. \nThank you for using Cliniq-IUL!\n"

 exit
 ;;

 esac    

done