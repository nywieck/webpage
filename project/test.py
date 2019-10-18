import random
def coinToss():
    print("\n\n\n\n_=[[  TIC - TAC - TOE  ]]=_\n\n-------- -------- --------\n\nWelcome player!\n")
    print("You flip a coin, and I'll make a guess to decide who goes first!\n")
    input("Press Enter to continue...")
    guess = random.randrange(0, 2, 1)
    if guess == 0:
        guess = "Heads"
    else:
        guess = "Tails"
    print(f"I guessed {guess}!\n\nWho won and will be going first?")
    while True:
        firstTurn = input("1: Computer\n2: Player\n(Enter 1 or 2 to continue):\n")
        try:
            choice = int(firstTurn)
            if choice < 1 or choice > 2:
                print("You have fat fingers - input must be the integer 1 or 2, try again!\n")
                continue
            else:
                return(choice)
        except ValueError:
            print("You have fat fingers - input must be the integer 1 or 2, try again!\n")

coinToss()
