const int redLED = 9;
const int greenLED = 10;
const int blueLED = 11;

int redValue = 0;
int newRedValue = 0;

int blueValue = 0;
int newBlueValue = 0;

int greenValue = 0;
int newGreenValue = 0;

int randomValue = 0;
int fadeSpeed = 50;

int mood;
const int moodTime = 10000;
void setup() {
  // put your setup code here, to run once:
  pinMode(redLED, OUTPUT);
  pinMode(blueLED, OUTPUT);
  pinMode(greenLED, OUTPUT);

  Serial.begin(9600);

}

void loop() {
  // put your main code here, to run repeatedly:
  //mood = random(20);
// Serial.println(mood);
  if(Serial.available() > 0){
  mood = Serial.read();
  
  if(mood == 'P'){
 //if(mood == 0){
    purr();
  }

  if(mood == 'H'){
 // if(mood==1){
    happy();
  }

 if(mood == 'S'){
 //if(mood == 2){
    sad();
  }
 if(mood == 'B'){
 //if(mood==3){
    blendColors();
  }

}
}

void blendColors(){

  if(redValue < newRedValue){
    for(int x = redValue; x<newRedValue; x++){
      analogWrite(redLED, x);
      delay(fadeSpeed);
    }
  }
  else{
    for(int x = redValue; x>newRedValue; x--){
      analogWrite(redLED, x);
      delay(fadeSpeed);
    }
  }

  redValue = newRedValue;

  if(greenValue < newGreenValue){
    for(int x = greenValue; x<newGreenValue; x++){
      analogWrite(greenLED, x);
      delay(fadeSpeed);
    }
  }
  else{
    for(int x = greenValue; x>newGreenValue; x--){
      analogWrite(greenLED, x);
      delay(fadeSpeed);
    }
  }

  greenValue = newGreenValue;

  if(blueValue < newBlueValue){
    for(int x = blueValue; x<newBlueValue; x++){
      analogWrite(blueLED, x);
      delay(fadeSpeed);
    }
  }
  else{
    for(int x = blueValue; x>newBlueValue; x--){
      analogWrite(blueLED, x);
      delay(fadeSpeed);
    }
  }

  blueValue = newBlueValue;
}

void purr(){

  for(int count = 0; count<10; count++){
    for(int x = 0; x<255; x++){
      analogWrite(redLED, x);
      analogWrite(greenLED, x);
      analogWrite(blueLED, x);
      delay(10);
    }

    for(int x = 255; x>0; x--){
      analogWrite(redLED, x);
      analogWrite(greenLED, x);
      analogWrite(blueLED, x);
      delay(10);
    }
  }
}

void happy(){

  for(int i = greenValue; i<255; i++)
  {
    analogWrite(greenLED, i);
    delay(fadeSpeed);
  }

  for(int i = redValue; i>0; i--)
  {
    analogWrite(redLED, i);
    delay(fadeSpeed);
  }

  for(int i = blueValue; i>0; i--)
  {
    analogWrite(blueLED, i);
    delay(fadeSpeed);
  }

  delay(moodTime);

  for(int x = 255; x>0; x--){
    analogWrite(greenLED, x);
    delay(fadeSpeed);
  }
}

void sad(){

  for(int i = redValue; i<255; i++){
    analogWrite(redLED, i);
    delay(fadeSpeed);
  }

   for(int x = 255; x>0; x--){
    analogWrite(greenLED, x);
    delay(fadeSpeed);
  }

  for(int i = blueValue; i>0; i--)
  {
    analogWrite(blueLED, i);
    delay(fadeSpeed);
  }

  delay(moodTime);

  for(int x = 255; x>0; x--){
    analogWrite(redLED, x);
    delay(fadeSpeed);
  }
}

