import RPi.GPIO as GPIO
import time
import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverAddress = ('10.42.0.63', 1337)
print ("Starting server at %s:%i"%serverAddress)
sock.bind(serverAddress)

red = 11
green = 13
blue = 15

GPIO.setmode(GPIO.BOARD)
GPIO.setup(red, GPIO.OUT)
GPIO.setup(green, GPIO.OUT)
GPIO.setup(blue, GPIO.OUT)

pr = GPIO.PWM(red, 255)
pr.start(0)
pg = GPIO.PWM(green, 255)
pg.start(0)
pb = GPIO.PWM(blue, 255)
pb.start(0)
sock.listen(1)
try:
	while 1:
		print ("Listening..")
		connection, client = sock.accept()
		print (client, "connected")

			
		try:
			while 1:
				data = connection.recv(256)
				r = ord(data[0])
				g = ord(data[1])
				b = ord(data[2])
				pr.ChangeDutyCycle(r/2.55)
				pg.ChangeDutyCycle(g/2.55)
				pb.ChangeDutyCycle(b/2.55)
		except:
			pass
except KeyboardInterrupt:
	pr.stop()
	pg.stop()
	pb.stop()
	GPIO.cleanup()
	sock.close()
