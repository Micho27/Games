import random

#guessing game

random.seed()
num=random.randint(0,100)
guesses=0

while True:
	guess=int(input("make a guess"))
	guesses+=1
	
	if guess<num:
		print('higher')
	if guess>num:
		print('Lower')
	if guess==num:
		print("Congratulations, it took you",guesses," attempts")
		break