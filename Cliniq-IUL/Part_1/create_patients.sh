#!/bin/sh

> pacientes.txt

patient_number=0
patient_name=''
patient_locality=''
patient_phone_number=''
patient_inital_balance=100
domain_iscte='@iscte-iul.pt'
number_of_patients=$(cat pacientes.txt | wc -l) 
i=1

while [ $number_of_patients -lt 10 ]; do
    
    patient_number=$(cat /etc/passwd | grep "^a[0-9]" | head -$i | tail -1 | cut -d ':' -f1 | sed 's/a//g')
    aux=$(cat pacientes.txt | cut -d ':' -f1 | sed 's/a//g' | grep -x "$patient_number" | wc -l)
    
    if [ $aux -ne 0 ]; then

        while [ $aux -ne 0 ]; do

			i=$(( $i + 1 ))
			patient_number=$(cat /etc/passwd | grep "^a[0-9]" | head -$i | tail -1 | cut -d ':' -f1 | sed 's/a//g')
			aux=$(cat pacientes.txt | cut -d ':' -f1 | sed 's/a//g' | grep -x "$patient_number" | wc -l)

        done
    fi

    patient_name=$(cat /etc/passwd | grep "^a[0-9]" | head -$i | tail -1 | cut -d ':' -f5 | sed 's/,//g')
    aux_name=$(cat /etc/passwd | grep "^a[0-9]" | head -$i | tail -1 | cut -d ':' -f1)
    patient_email=$aux_name$domain_iscte
    patient=$patient_number';'$patient_name';'$patient_locality';'$patient_phone_number';'$patient_email';'$patient_inital_balance

    echo  $patient >> pacientes.txt

    i=$(( $i + 1 )) 

    number_of_patients=$(cat pacientes.txt | wc -l) 

done