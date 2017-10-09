const int buttonPin = 2;

int pressCount = 0;
unsigned long lastDebounceTime = 0;
unsigned long debounceDelay = 5;


int lastButtonState = LOW;
int buttonState = LOW;
unsigned long nextCountTime = 0;

void buttonPressed() {
  //Serial.println("1");
  int reading = digitalRead(buttonPin);
  if(reading != lastButtonState){
    //Serial.println("2");
    lastDebounceTime = millis();
  }
  
  lastButtonState = reading;
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);

  // Interrupts can happen on "edges" of signals.  
  // Three edge types are supported: CHANGE, RISING, and FALLING 
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
  if((millis()-lastDebounceTime)>debounceDelay){
    int reading = digitalRead(buttonPin);
    if(buttonState == LOW&&reading==HIGH){
      ++pressCount;
      //Serial.println("4");
    }
    
    buttonState = reading;

  }
  if(millis()>nextCountTime){
    Serial.println(pressCount);
    nextCountTime += 1000;
  }
  
}
