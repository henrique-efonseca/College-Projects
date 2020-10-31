#!/bin/bash

touch lista_negra_medicos.txt
touch medicos.txt

count=0
i=1

if [ $(cat medicos.txt | wc -l) -eq 0 ]; then

    echo "Error. There are no Doctors registered. Please register a Doctor to use Black List."
    exit

else  

    echo "$(cat medicos.txt | sed '/^[[:space:]]*$/d')" > medicos.txt
    doctors_registered=$(cat medicos.txt | wc -l)

fi

if [ $(cat lista_negra_medicos.txt | wc -l) -ne 0 ]; then
  
    echo "$(cat lista_negra_medicos.txt | sed '/^[[:space:]]*$/d')" > lista_negra_medicos.txt

fi

while [ $i -le $doctors_registered ]; do 
    
    doctor=$(cat medicos.txt | head -$i | tail -1)
    doctor_name=$(cat medicos.txt | head -$i | tail -1 | cut -d ';' -f1)
    doctor_rating=$(cat medicos.txt | head -$i | tail -1 | cut -d ';' -f5)
    doctor_appointments=$(cat medicos.txt | head -$i | tail -1 | cut -d ';' -f6)
    doctor_card_number=$(cat medicos.txt | cut -d ';' -f2 | head -$i | tail -1)
    doctor_email=$(cat medicos.txt | cut -d ';' -f4 | head -$i | tail -1)

    doctor_registered_number=$(cat lista_negra_medicos.txt | cut -d ';' -f2 | grep -x "$doctor_card_number" | wc -l)
    doctor_registered_email=$(cat lista_negra_medicos.txt | cut -d ';' -f4 | grep -i "$doctor_email" | wc -l)

    if [[ $doctor_rating -lt 5 && $doctor_appointments -gt 6 && $doctor_registered_number -eq 0 && $doctor_registered_email -eq 0 ]]; then

        echo  "| Doctor $doctor_name Information |"
        echo  "Appointments Concluded: $doctor_appointments"
        echo -e "Rating: $doctor_rating \n"
        read -r -p "Do you want to add Doctor $doctor_name to the Black List? (Y/n) " option

        count=$(( $count + 1 ))

        if [[ $option == "Y"  ||  $option == "y" ]]; then
                
            echo "$doctor" >> lista_negra_medicos.txt
            echo -e "Doctor $doctor_name Added to the Black List. \n"
            echo -e "---------------------------------------------------- \n"

        else

            echo -e "Doctor $doctor_name Not added to the Black List. \n"
            echo -e "---------------------------------------------------- \n"

         fi
    fi

    i=$(( $i + 1 ))

done

if [ $count -eq 0 ]; then

    echo "There are a total of 0 Doctors with a number of Appointments Concluded greater than 6 and a Rating less than 5."

fi

echo -e "Here are all the Doctors in the Black List: \n"
cat lista_negra_medicos.txt
echo 