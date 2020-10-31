#!/bin/bash

touch pacientes.txt
touch medicos.txt

location=$1
salary=$2
number_patients=0
number_doctors=0
i=1

if [ "$#" -eq 2 ]; then

    if [ $(cat pacientes.txt | wc -l) -eq 0 ] ; then

        ./cria_pacientes.sh

    else

        echo "$(cat pacientes.txt | sed '/^[[:space:]]*$/d')" > pacientes.txt
            
    fi

    if [ $(cat medicos.txt | wc -l) -eq 0 ]; then

        echo "Error. There are no Doctors registered. Please register a Doctor to use Stats."
        exit

    else

        echo "$(cat medicos.txt | sed '/^[[:space:]]*$/d')" > medicos.txt
        doctors_registered=$(cat medicos.txt | wc -l)

    fi

    if [[ $salary == 0* ]]; then

        echo "Error. Balance can't start with 0."
        exit

    fi

    if [ "$salary" -ge 0 ] 2>/dev/null ; then
        
        if [[ "$location" =~ [0-9] ]]; then

            echo "Error. Location can't have integers."
            exit 

        else

            number_patients=$(cat pacientes.txt | cut -d ';' -f3 | grep -i -x $location | wc -l)

            while [ $i -le $doctors_registered ]; do 

                aux_salary=$(cat medicos.txt | cut -d ';' -f7 | head -$i | tail -1)

                if [[ ! -z "$aux_salary" && $aux_salary -gt $salary ]]; then

                    number_doctors=$(( $number_doctors + 1 ))

                fi

                i=$(( $i + 1 ))

            done

            echo "Number of Patients from $location: $number_patients"
            echo "Number of Doctors with a Balance greater than $salary: $number_doctors"

        fi

    else

        echo "Error. Balance must be a positive integer."
        exit

    fi

else

echo "Arguments are not equal to 2."    
exit 

fi