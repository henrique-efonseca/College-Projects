#!/bin/bash

touch medicos.txt

doctor_name=$1
doctor_card_number=$2
doctor_speciality=$3
doctor_email=$4
doctor_rating=0
doctor_appointments=0
doctor_balance=0
doctor=''

if [ "$#" -eq 4 ] ; then

    if [ $(cat medicos.txt | wc -l) -ne 0 ]; then

        echo "$(cat medicos.txt | sed '/^[[:space:]]*$/d')" > medicos.txt

    fi

    aux_card=$(cat medicos.txt | cut -d ';' -f2 | grep -x "$doctor_card_number" | wc -l)
    aux_email=$(cat medicos.txt | cut -d ';' -f4 | grep -i "$doctor_email" | wc -l)

    if [ "$doctor_card_number" -ge 0 ] 2>/dev/null ; then

        if [[ $doctor_name =~ [0-9] ]]; then

            echo "Error. Doctor's name can't have integers."
            exit 

        elif [[ $doctor_speciality =~ [0-9] ]]; then

            echo "Error. Medical Speciality can't have integers."
            exit 
            
	    elif [[ $doctor_email != *'@'* ]]; then

			echo "Error. Invalid email (must contain "@")."
			exit 
               
        else

            if [ "$aux_card" -ne 0 ] ; then

                echo -e "Error. Doctor already registered.\n"
                echo "Here is the Doctor registered: "
                echo $(cat medicos.txt | grep ";$doctor_card_number;")
                exit

            fi

            if [ "$aux_email" -ne 0 ]; then
            
                echo -e "Error. Doctor already registered.\n"
                echo "Here is the Doctor registered: "
                echo $(cat medicos.txt | grep ";$doctor_email;")
                exit

            fi

            doctor=$doctor_name';'$doctor_card_number';'$doctor_speciality';'$doctor_email';'$doctor_rating';'$doctor_appointments';'$doctor_balance
            echo "$doctor" >> medicos.txt
			echo -e "Doctor $doctor_name added to the platform successfully! \n"
            echo -e "Here are all the Doctors in Clinic-IUL: \n"
            cat medicos.txt
            exit 
                
        fi

    else

        echo "Error. Professional Card Number must be a positive integer."
        exit

    fi

else

echo "Arguments are not equal to 4."    
exit 

fi