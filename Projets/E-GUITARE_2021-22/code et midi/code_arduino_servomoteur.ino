#include <Servo.h>

Servo servomoteur2;
   Servo servomoteur1;
   Servo servomoteur3;
  int partition[]={1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 4, 4, 3, 3, 3, 3, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 0, 0, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
  int i;
  int positionA;



void setup() {
  // put your setup code here, to run once:
  servomoteur2.attach(5);
  servomoteur1.attach(7);
  servomoteur3.attach(2);
  
  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
  
  for (i=0;i<=sizeof(partition);i++){
  Serial.println(partition[i]);
  positionA=partition[i];
  
  if (positionA==2) {
    servomoteur2.write(110);
    delay(125);
  }
if (positionA==1) {
    servomoteur1.write(70);
    delay(125);
    
  }
  if (positionA==0) {
    servomoteur1.write(90);
    servomoteur2.write(90);
    servomoteur3.write(90);
    delay(125);
    
  }
  if (positionA==3) {
    servomoteur2.write(70);
    delay(125);
    
  }
  if (positionA==4) {
    servomoteur3.write(70);
    delay(125);
  }
  
  }

}
