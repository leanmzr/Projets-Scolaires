# Code ARDUINO

```c
//Code éxécutée lorsque l'IA détecte un T-shirt

#include <Servo.h>
//Je défini le type des variable
Servo servomoteur1;
Servo servomoteur2;
Servo servomoteur3;

int i;

void setup() {
//Je relie les servomoteurs aux pins de l'arduino
servomoteur1.attach(6);
servomoteur2.attach(5);
servomoteur3.attach(3);

//Je définis la position initiale des servomoteur, 
//Pour le servomoteur 1, nous voulons le faire tourner dans le sens trigo donc on l'initilise à la position 0 degrés
servomoteur1.write(0);

//Pour les deux autres, on initilise leur position à 135 degrés pour le faire revenir à 0 deg pour le faire tourner dans le sens anti-trigo
servomoteur2.write(135);
servomoteur3.write(135);

delay(5000);
Serial.begin(9600);

}

void loop() {
  
  for (i=0;i<=5;i++){
//Je définis l'ordre des rotations des servomoteurs suivants le pliage voulu
  
  if (i==0) {
    
    servomoteur1.write(135);
    delay(2000);
    
    
  }
if (i==1) {
    servomoteur1.write(0);
    delay(2000);
    
  }
  if (i==2) {
    
    servomoteur2.write(0);
    
    delay(2000);
    
  }
  if (i==3) {
    servomoteur2.write(135);
 
    delay(2000);
    
  }
  if (i==4) {
    servomoteur3.write(0);

    delay(2000);
    
  }
  if (i==5) {
    servomoteur3.write(135);

    delay(2000);
    
  }
  }
}
```

```c
//Code éxécutée lorsque l'IA détecte un pantalon

#include <Servo.h>

Servo servomoteur1;
Servo servomoteur2;
Servo servomoteur3;

int i;

void setup() {
servomoteur1.attach(6);
servomoteur2.attach(5);
servomoteur3.attach(3);

//On initialise les moteurs à une position initiale
servomoteur1.write(0);
servomoteur2.write(135);
servomoteur3.write(135);

delay(5000);
Serial.begin(9600);

}

void loop() {
  
  for (i=0;i<=5;i++){

  
  if (i==0) {

    
   servomoteur3.write(0);

    delay(2000);
    
    
  }
if (i==1) {
    servomoteur3.write(135);
    delay(2000);
    
  }
  if (i==2) {
    
    servomoteur1.write(135);
    delay(2000);
    
  }
  if (i==3) {
    servomoteur1.write(0);
    delay(2000);
    
  }
  if (i==4) {
    servomoteur2.write(0);
    delay(2000);
    
  }
  if (i==5) {
    servomoteur2.write(135);
    delay(2000);
    
  }
  }
}
```