#tic tact toe game

import random

#game board
board=[0,1,2,3,4,5,6,7,8]

#printing board
def show():
	print (board[0],'|',board[1],'|',board[2])
	print ('----------')
	print (board[3],'|',board[4],'|',board[5])
	print ('----------')
	print (board[6],'|',board[7],'|',board[8])

def win(char,spot1,spot2,spot3):
    if board[spot1]==char and board[spot2]==char and board[spot3]==char:
        return True

def checkall(char):
    #check lines
    if win(char,0,1,2):
        return True
    if win(char,3,4,5):
        return True
    if win(char,6,7,8):
        return True
    
    #check columns
    if win(char,0,3,6):
        return True
    if win(char,1,4,7):
        return True
    if win(char,2,5,8):
        return True
    
    #check diagonals
    if win(char,0,4,8):
        return True
           
    if win(char,2,4,6):
        return True
		
#main game

while True:
	try:
		spot=int(input("pick a spot: "))
				
		if board[spot] != 'x' and board[spot]!='o':
			board[spot]='x'
	except:
		print("Invalid input.")
		continue 
		
    #check winner
	if checkall('x'):
		show()
		print('~~ x wins~~')
		break
            
        #computers turn
	while True:
		random.seed()#generates random
		opponent = random.randint(0,8)
        
		if board[opponent]!='x' and board[opponent]!='o':
			board[opponent]='o'
			break
    #check winner
	if checkall('o'):
		show()
		print('~~o wins~~')
		break
        
	else:
		print("This spot is taken")

	show()
