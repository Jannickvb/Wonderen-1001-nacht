//Pressure plate pins:
int pressurePlate1 = 4;
int pressurePlate2 = 5;
int pressurePlate3 = 6;
int pressurePlate4 = 7;

//Led pins (for testing):
int ledPin1 = 10;
int ledPin2 = 11;
int ledPin3 = 12;
int ledPin4 = 13;

void setup() {
  //Pressure plate pin modes:
  pinMode(pressurePlate1,INPUT); //plate 1
  pinMode(pressurePlate2,INPUT); //plate 2
  pinMode(pressurePlate3,INPUT); //plate 3
  pinMode(pressurePlate4,INPUT); //plate 4
  //Led pinmodes:
  pinMode(ledPin1,OUTPUT);
  pinMode(ledPin2,OUTPUT);
  pinMode(ledPin3,OUTPUT);
  pinMode(ledPin4,OUTPUT);
  //Starting serial communication
  Serial.begin(9600);
}

void loop() {
   //tetsPressurePlates();
   if(Serial.available() > 0) {
     String word = "";
     while(Serial.available() > 0) {
       char letter = Serial.read();
        word += letter;
        delay(3);
     }
     Serial.println("I got this: " + word);
     
     if(word == "L1E") {
       digitalWrite(ledPin4,HIGH);
     }
     if(word == "L1D") {
       digitalWrite(ledPin4,LOW);
     }
     
     delay(3); 
  }
}

void tetsPressurePlates() {
  int pressureRead1 = digitalRead(pressurePlate1);
  int pressureRead2 = digitalRead(pressurePlate2);
  int pressureRead3 = digitalRead(pressurePlate3);
  int pressureRead4 = digitalRead(pressurePlate4);
  //Pressure plate 1:
  if(pressureRead1 == LOW) {
    Serial.println("y");  
    digitalWrite(ledPin4,HIGH);
    delay(500);
  }
  else { 
    digitalWrite(ledPin4,LOW);
  }
  //Pressure plate 2:
  if(pressureRead2 == LOW) {
    digitalWrite(ledPin3,HIGH);
  }
  else { 
    digitalWrite(ledPin3,LOW);
  }
  //Pressure plate 3:
  if(pressureRead3 == LOW) {
    digitalWrite(ledPin2,HIGH);
  }
  else { 
    digitalWrite(ledPin2,LOW);
  }
  //Pressure plate 4:
  if(pressureRead4 == LOW) {
    digitalWrite(ledPin1,HIGH);
  }
  else { 
    digitalWrite(ledPin1,LOW);
  }
}
