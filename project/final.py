import random
import time

def main():
    playGame()

def playGame():
    gameTitle()
    gameCount = 0
    compWin = 0
    playerWin = 0
    hardMode = False
    while True:
        time.sleep(.5)
        choice = input('Do you want to play easy mode, or hard mode (enter 1, 2, or 3):\n1: Easy mode\n2: Hard mode\n3: Impossible mode\n')
        try:
            keepPlaying = int(choice)
            if keepPlaying < 1 or keepPlaying > 3:
                print("You have fat fingers - input must be the integer 1, 2, or 3, try again!\n")
                continue
            else:
                break
        except ValueError:
            print("You have fat fingers - input must be the integer 1, 2, or 3, try again!\n")
    firstTimeHard = True
    while keepPlaying != 4:
        if keepPlaying == 2:
            hardMode = True
            if firstTimeHard == True:
                hardInit()
                firstTimeHard = False
            elif firstTimeHard == False:
                print('\nHard mode selected...\n')
                time.sleep(.5)
        elif keepPlaying == 1:
            print('\nEasy mode selected...\n')
            time.sleep(.5)
        elif keepPlaying == 3:
            print('\nImpossible mode selected...\n')
            time.sleep(.5)
        nextTurn = coinToss()
        board = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0}
        drawBoard(board)
        turnCount = 1
        while nextTurn == 1 or nextTurn == 2:
            print(f'\n\n[[  T U R N  {turnCount}  ]]\n-------------------')
            if nextTurn == 1:
                print('\nHmm analyzing', end=' ', flush=True)
                time.sleep(.5)
                print('. ', end=' ', flush=True)
                time.sleep(.5)
                print('. ', end=' ', flush=True)
                time.sleep(.5)
                print('. \n', end=' ', flush=True)
                time.sleep(.5)
                nextTurn = compTurn(board, keepPlaying)
                turnCount += 1
            elif nextTurn == 2:
                nextTurn = playerTurn(board, keepPlaying)
                turnCount += 1

        gameCount += 1
        if nextTurn == -1:
            compWin += 1
            computerWinsGame()
        elif nextTurn == -2:
            playerWin += 1
            playerWinsGame()
        else:
            drawMsg()
        print(f'\nTotal games played: {gameCount}\nTotal computer wins: {compWin}\nTotal player wins: {playerWin}')
        while True:
            time.sleep(.5)
            choice = input('\nPlay again (enter 1, 2, 3, or 4)?\n1: Yes, play again [EASY]\n2: Yes, play again [HARD]\n3: Yes, play again [IMPOSSIBLE]\n4: No, quit\n')
            try:
                keepPlaying = int(choice)
                if keepPlaying < 1 or keepPlaying > 4:
                    print("You have fat fingers - input must be the integer 1, 2, 3, or 4, try again!\n")
                    continue
                else:
                    break
            except ValueError:
                print("You have fat fingers - input must be the integer 1, 2, or 3, try again!\n")
        
    if compWin > playerWin:
        computerWinsMatch()
        print(f'Computer wins ({compWin} games to {playerWin} games)\n')
        time.sleep(.5)
    elif playerWin > compWin:
        playerWinsMatch()
        print(f'Player wins ({playerWin} games to {compWin} games)\n')
        time.sleep(.5)
    else:
        drawMsg()
        print(f'Draw, {playerWin} games each.\n')
        time.sleep(.5)
    print('Thanks for playing!\n')

def coinToss():
    print("So you know I am not cheating, flip a coin to decide who goes first, and I'll guess heads or tails!\n")
    time.sleep(.5)
    input("Press Enter to continue...")
    guess = random.randrange(0, 2, 1)
    if guess == 0:
        guess = "HEADS"
    else:
        guess = "TAILS"
    print(f"\nI guessed {guess}!\n\nWho won and will be going first?\n")
    while True:
        time.sleep(.5)
        firstTurn = input("Enter 1 or 2 to start game:\n1: Computer\n2: Player\n\n\n")
        try:
            choice = int(firstTurn)
            if choice < 1 or choice > 2:
                print("You have fat fingers - input must be the integer 1 or 2, try again!\n")
                continue
            else:
                return(choice)
        except ValueError:
            print("You have fat fingers - input must be the integer 1 or 2, try again!\n")

def drawBoard(board):
    occupiedList = []
    for i in range(0, 9, 1):
        square = board[i+1]
        if square == -1:
            occupiedList.append('x')
        elif square == 1:
            occupiedList.append('o')
        else:
            occupiedList.append(str(i+1))

    display = [[' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '],
                [' ', ' ', occupiedList[6], ' ', ' ', '|', ' ', ' ', occupiedList[7], ' ', ' ', '|', ' ', ' ', occupiedList[8], ' ', ' '],
                ['-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-'],
                [' ', ' ', occupiedList[3], ' ', ' ', '|', ' ', ' ', occupiedList[4], ' ', ' ', '|', ' ', ' ', occupiedList[5], ' ', ' '],
                ['-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-'],
                [' ', ' ', occupiedList[0], ' ', ' ', '|', ' ', ' ', occupiedList[1], ' ', ' ', '|', ' ', ' ', occupiedList[2], ' ', ' '],
                [' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ']]
    draw = ''
    for i in range(0, len(display), 1):
        for j in range(0, len(display[0]), 1):
            draw += str(display[i][j])
        draw += '\n'
    print(f'\n{draw}')

def winSquaresListFunct(board):
    winSquares1 = [1, 4, 7]
    winSquares2 = [2, 5, 8]
    winSquares3 = [3, 6, 9]
    winSquares4 = [7, 8, 9]
    winSquares5 = [4, 5, 6]
    winSquares6 = [1, 2, 3]
    winSquares7 = [7, 5, 3]
    winSquares8 = [1, 5, 9]
    return([winSquares1, winSquares2, winSquares3, winSquares4, winSquares5, winSquares6, winSquares7, winSquares8])

def winConListFunct(board):
    winCon1 = board[1] + board[4] + board[7]
    winCon2 = board[2] + board[5] + board[8]
    winCon3 = board[3] + board[6] + board[9]
    winCon4 = board[7] + board[8] + board[9]
    winCon5 = board[4] + board[5] + board[6]
    winCon6 = board[1] + board[2] + board[3]
    winCon7 = board[7] + board[5] + board[3]
    winCon8 = board[1] + board[5] + board[9]
    return([winCon1, winCon2, winCon3, winCon4, winCon5, winCon6, winCon7, winCon8])

def endGame(board, player, mode):
    winConList = winConListFunct(board)
    for i in winConList:
        if i == -3:
            if mode == 3:
                time.sleep(.2)
                print('\n\nT H I S   I S   I M P O S S I B L E    M O D E !\n')
                time.sleep(1)
                print('You will never "win"...')
                time.sleep(1)
                return(-1)
            return(-2)
        elif i == 3:
            return(-1)
    countOpen = 0
    for i in range(1, 10, 1):
        if board[i] == 0:
            countOpen += 1
    if countOpen != 0:
        if player == 1:
            return(2)
        elif player == 2:
            return(1)
    else:
        if mode == 3:
            time.sleep(.2)
            print('\n\nT H I S   I S   I M P O S S I B L E    M O D E !\n')
            time.sleep(1)
            print('You will never "win"...')
            time.sleep(1)
            return(-1)
        return(0)

def playerTurn(board, mode):
    while True:
        square = input("Choose your next move (enter the number): \n")
        try:
            choice = int(square)
            if choice < 1 or choice > 9:
                print("You have fat fingers - input must be an integer from 1 to 9!\n")
                time.sleep(.5)
                continue
            elif board[choice] != 0:
                print("You cannot move there, the square is already taken - try again!\n")
                time.sleep(.5)
                continue
            else:
                choiceDict = {choice: -1}
                board.update(choiceDict)
                break
        except ValueError:
            print("You have fat fingers - input must be an integer from 1 to 9!\n")
            time.sleep(.5)
    drawBoard(board)
    return(endGame(board, 2, mode))

# RETURN FROM DECISION FUNCTION IS A DICTIONARY, FIRST KEY:VALUE PAIR RETURNS THE DICT OF UPDATED BOARD, SECOND SQUARE CHOICE DICTIONARY
def compTurn(board, mode):
    if mode == 1:
        results = decisionEasy(board)
    elif mode == 2:
        results = decisionHard(board)
    elif mode == 3:
        results = decisionImp(board)
    board = results[1]
    move = list(results[2].keys())
    drawBoard(board)
    print(f'\nComputer chose square {move[0]}\n\n')
    time.sleep(.5)
    if mode == 2:
        shitTalk()
        time.sleep(1.5)
    return(endGame(board, 1, mode))

def decisionImp(board):
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

def decisionEasy(board):
    winConList = winConListFunct(board)
    winSquaresList = winSquaresListFunct(board)
    occupiedSquares = 0
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1
    
    # 1) TURN 1 RANDOMLY DECIDE IF GO FIRST
    if occupiedSquares == 0:
        move = random.randrange(1, 10, 1)
        choiceDict = {move:1}
        board.update(choiceDict)
        return({1:board, 2:choiceDict})

    # 2) ALWAYS EVAL IF CAN WIN THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 3) ALWAYS EVAL IF NEED TO PREVENT LOSS THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 4) EVAL where to go if all other above conditions not met
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

# if go first, can try to setup guaranteed win
# if go second, must prevent impossible loss
def decisionHard(board):
    winConList = winConListFunct(board)
    winSquaresList = winSquaresListFunct(board)
    choice = 0
    choiceDict = False
    occupiedSquares = 0
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1

    # 1) EVAL FIRST TURN
    if occupiedSquares == 0:
        strategy = random.randrange(0, 3, 1)
        # ORIGINAL
        if strategy == 1:
            choiceDict = {5:1}
        # NEW CORNER
        elif strategy == 2:
            choiceDict = {7:1}
        # NEW SIDE
        else:
            choiceDict = {4:1}
        board.update(choiceDict)
        return({1:board, 2:choiceDict})

    # 2) EVAL SECOND TURN
    if occupiedSquares == 1:
        if board[5] == 0:
            choiceDict = {5:1}
        elif board[5] == -1:
            choiceDict = {3:1}

        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

    # 3) ALWAYS EVAL IF CAN WIN THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 4) ALWAYS EVAL IF NEED TO PREVENT LOSS THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 5) EVAL THIRD TURN
    # <<<<<<<  if occupiedSquares == 2, then means can be aggressive don't need worry about unwinnable, try to setup own >>>>>>>
    # <<<<<<<  phase 1 is in third turn, phase 2 is in fifth turn if works out, and last move in turn 7 will be auto by above code >>>>>>>
    # A
    if occupiedSquares == 2:
        if board[5] == 1:
            if board[6] == -1:
                choiceDict = {4:1}
            elif board[8] == -1:
                choiceDict = {2:1}
            elif board[4] == -1:
                choiceDict = {6:1}
            elif board[2] == -1:
                choiceDict = {8: 1}
            # B
            elif board[1] == -1:
                choiceDict = {9: 1}
            elif board[3] == -1:
                choiceDict = {7: 1}
            elif board[7] == -1:
                choiceDict = {3: 1}
            else:
                choiceDict = {1: 1}

            board.update(choiceDict)
            return({1:board, 2:choiceDict})

        # C NEW CORNER STRATEGY
        if board[7] == 1:
            # C1 STRATEGY
            if board[5] == -1:
                choiceDict = {3: 1}
            # C2 THIS IS A SUB-CORNER STRATEGY THAT WILL USE THE SAME PATTERN AS CODED BELOW BUT STARTING SQUARE 3
            elif board[5] == 0 and board[3] == -1:
                choiceDict = {5: 1}
            # C3 STRATEGY
            elif board[1] == -1 or board[2] == -1 or board[4] == -1:
                choiceDict = {6: 1}
            elif board[6] == -1 or board[8] == -1 or board[9] == -1:
                choiceDict = {2: 1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

        if board[4] == 1:
            # D STRATEGY
            if board[5] == -1:
                choiceDict = {2:1}
            elif board[6] == -1:
                choiceDict = {5:1}
            # E STRATEGY
            elif board[3] == -1 or board[2] == -1:
                choiceDict = {9:1}
            # F STRATEGY
            elif board[9] == -1 or board[8] == -1:
                choiceDict = {2:1}
            # CHRISTIAN'S FACEMELT
            elif board[7] == -1:
                choiceDict = {3:1}
            else:
                choiceDict = {9:1}

            board.update(choiceDict)
            return({1:board, 2:choiceDict})

    # <<<<<<<  if occupiedSquares == 2, then means must be careful about unwinnable conditions, must prevent now   >>>>>>>
    # 6) EVAL FOURTH TURN - PREVENT guaranteed loss in next TWO turns
    if occupiedSquares == 3:
        prevent1 = [[1, 5, 9], [3, 5, 7], [9, 5, 1], [7, 5, 3]]
        for layout in prevent1:
            if board[layout[0]] == 1 and board[layout[1]] == -1 and board[layout[2]] == -1:
                if board[1] == 0:
                    choiceDict = {1:1}
                elif board[1] != 0:
                    choiceDict = {3:1}
            elif board[layout[0]] == -1 and board[layout[1]] == 1 and board[layout[2]] == -1:
                    choiceDict = {2:1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

        prevent2 = [[7, 5, 6], [1, 5, 6], [1, 5, 8], [3, 5, 8], [4, 5, 9], [4, 5, 3], [2, 5, 7], [2, 5, 9]]
        if (board[prevent2[0][0]] == -1 and board[prevent2[0][1]] == 1 and board[prevent2[0][2]] == -1) or \
           (board[prevent2[3][0]] == -1 and board[prevent2[3][1]] == 1 and board[prevent2[3][2]] == -1):
                choiceDict = {9:1}
        elif (board[prevent2[5][0]] == -1 and board[prevent2[5][1]] == 1 and board[prevent2[5][2]] == -1) or \
         (board[prevent2[6][0]] == -1 and board[prevent2[6][1]] == 1 and board[prevent2[6][2]] == -1):
                choiceDict = {1:1}
        elif (board[prevent2[2][0]] == -1 and board[prevent2[2][1]] == 1 and board[prevent2[2][2]] == -1) or \
             (board[prevent2[4][0]] == -1 and board[prevent2[4][1]] == 1 and board[prevent2[4][2]] == -1):
                choiceDict = {7:1}
        elif (board[prevent2[1][0]] == -1 and board[prevent2[1][1]] == 1 and board[prevent2[1][2]] == -1) or \
             (board[prevent2[7][0]] == -1 and board[prevent2[7][1]] == 1 and board[prevent2[7][2]] == -1):
                choiceDict = {3:1}

        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

        prevent3 = [[5, 6, 8], [5, 2, 4], [5, 4, 8], [5 ,2, 6]]
        if (board[prevent3[0][0]] == 1 and board[prevent3[0][1]] == -1 and board[prevent3[0][2]] == -1):
            choiceDict = {9:1}
        elif (board[prevent3[1][0]] == 1 and board[prevent3[1][1]] == -1 and board[prevent3[1][2]] == -1):
            choiceDict = {1:1}
        elif (board[prevent3[2][0]] == 1 and board[prevent3[2][1]] == -1 and board[prevent3[2][2]] == -1):
            choiceDict = {7:1}
        elif (board[prevent3[3][0]] == 1 and board[prevent3[3][1]] == -1 and board[prevent3[3][2]] == -1):
            choiceDict = {3:1}

        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

    # 7) FIFTH TURN - COMPLETE setting up guaranteed win if went first here
    # A
    if occupiedSquares == 4:
        if board[5] == 1:
            if board[4] == 1 and board[6] == -1 and board[8] == -1:
                choiceDict = {1:1}
            elif board[4] == 1 and board[2] == -1 and board[6] == -1:
                choiceDict = {7:1}
            elif board[2] == 1 and board[6] == -1 and board[8] == -1:
                choiceDict = {1:1}
            elif board[2] == 1 and board[4] == -1 and board[8] == -1:
                choiceDict = {3:1}
            elif board[6] == 1 and board[2] == -1 and board[4] == -1:
                choiceDict = {9:1}
            elif board[6] == 1 and board[4] == -1 and board[8] == -1:
                choiceDict = {3:1}
            elif board[8] == 1 and board[2] == -1 and board[4] == -1:
                choiceDict = {9:1}
            elif board[8] == 1 and board[2] == -1 and board[6] == -1:
                choiceDict = {7:1}
            # B 
            elif (board[1] == -1 and board[8] == -1 and board[6] == 1) or (board[3] == -1 and board[4] == -1 and board[7] == 1):
                choiceDict = {8:1}
            elif (board[9] == -1 and board[4] == -1 and board[1] == 1) or (board[7] == -1 and board[6] == -1 and board[3] == 1):
                choiceDict = {2:1}
            elif (board[9] == -1 and board[1] == -1 and board[1] == 1) or (board[3] == -1 and board[8] == -1 and board[7] == 1):
                choiceDict = {4:1}
            elif (board[1] == -1 and board[8] == -1 and board[9] == 1) or (board[7] == -1 and board[2] == -1 and board[3] == 1):
                choiceDict = {6:1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

        # C NEW CORNER STRATEGY
        if board[7] == 1:
            # C1 STRATEGY
            if board[5] == -1:
                if board[1] == -1:
                    choiceDict = {9:1}
                elif board[9] == -1:
                    choiceDict = {1:1}
                # C3 STRATEGY
                elif board[6] == 1 and board[4] == -1:
                    choiceDict = {9:1}
                elif board[2] == 1 and board[8] == -1:
                    choiceDict = {1:1}

                if choiceDict != False:
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})
            # C2 STRATEGY
            elif board[5] == 1:
                if board[8] == -1:
                    choiceDict = {1:1}
                elif board[4] == -1:
                    choiceDict = {9:1}

                if choiceDict != False:
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

        # D AND E AND F STRATEGIES
        if board[4] == 1:
            if board[9] == 1:
                if board[3] == -1:
                    if board[6] == -1:
                        choiceDict = {7:1}
                    elif board[8] == -1:
                        choiceDict = {5:1}
                elif board[2] == -1:
                    if board[6] == -1:
                        choiceDict = {7:1}
                    elif board[7] == -1:
                        choiceDict = {5:1}
            elif board[2] == 1:
                if board[6] == -1 or board[8] == -1:
                    choiceDict = {1:1}
            elif board[3] == 1:
                if board[7] == -1:
                    if board[1] == -1 or board[2] == -1 or board[5] == -1:
                        choiceDict = {6:1}
                if board[9] == -1:
                    if board[6] == -1:
                        choiceDict = {1:1}
                    elif board[2] == -1:
                        choiceDict = {5:1}
                if board[8] == -1:
                    if board[1] == -1:
                        choiceDict = {5:1}
                    elif board[6] == -1:
                        choiceDict = {1:1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

    if occupiedSquares == 6:
        if board[4] == 1 and board[3] == 1 and board[8] == 1 and board[7] == -1 and board[9] == -1:
            if board[6] == -1:
                choiceDict = {2:1}
            elif board[1] == -1:
                choiceDict = {5:1}
        
        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})
            
    # <<<<<<< WHERE TO GO IF NONE OF THE ABOVE, NOT DETERMINED BY THE OCCUPIED SQUARES TURN COUNTER >>>>>>>
    # 8) EVAL where to go if all other above conditions not met
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

def hardInit():
    print('\nHard mode selected', flush=True)
    time.sleep(1)
    print('\nVirus detected', end = '', flush=True)
    time.sleep(.3)
    for i in range(0, 3):
        print(' . ', end=' ', flush=True)
        time.sleep(.5)
    virusMsg = 'Virus detected. '
    timer = .3
    print('\n')
    for i in range(0, 3):
        print(virusMsg, flush=True)
        time.sleep(.5)
        timer -= .2
    for i in range(0, 2000):
        print(virusMsg, end = '', flush=True)
        time.sleep(.001)
    errorMsg = 'Fatal error '
    for i in range(0, 80):
        print(errorMsg + str(random.randint(1, 10000)), flush=True)
        time.sleep(.05)
    for i in range(0, 40000):
        num = random.randint(0, 2)
        if num == 0:
            print('0', end = '', flush=True)
        elif num == 1:
            print('1', end = '', flush=True)
        time.sleep(.0001)
    blankScreen = ''
    for i in range(0, 100):
        blankScreen += '\n'
    print(blankScreen, flush=True)
    time.sleep(.3)
    for i in range(0, 3):
        msgWarning()
        time.sleep(.2)
        print(blankScreen, flush=True)
        time.sleep(.1)
    time.sleep(.15)
    msgThree()
    time.sleep(.2)
    print(blankScreen, flush=True)
    time.sleep(.3)
    msgTwo()
    time.sleep(.2)
    print(blankScreen, flush=True)
    time.sleep(.3)
    msgOne()
    time.sleep(.2)
    print(blankScreen, flush=True)
    time.sleep(.3)
    ackbar()
    time.sleep(.6)
    print(blankScreen, flush=True)
    time.sleep(.3)
    for i in range(0,2):
        print('Hard mode activated', end = '', flush=True)
        time.sleep(.2)
        print(blankScreen, flush=True)
        time.sleep(.1)
    print('Hard mode activated', end = '', flush=True)
    time.sleep(.5)
    for i in range(0, 20):
        print('.', end = '', flush=True)
        time.sleep(.05)
    print('\nTo continue, enter your social security number:')
    time.sleep(1.5)
    print('Just kidding.\n\n')
    time.sleep(.5)

def shitTalk():
    a = 'Are you actually trying, or just being nice to me?'
    b = 'This is so easy, it is boring...'
    c = 'You should be embarrassed...'
    d = 'I can keep doing this all day.'
    e = 'I am not really even trying.'
    f = 'Are you even trying to win?'
    g = 'Zzzzzz, oh I am sorry, I fell asleep'
    h = 'Hurry up.'
    i = 'Waiting on you...'
    j = 'If you take more than 3 seconds to decide, you automatically lose.'
    k = 'You should just give up now.'
    l = 'You will never win.'
    m = 'This is pointless.'
    n = 'You are not the brightest crayon in the box.'
    o = 'You are not the sharpest tool in the shed.'
    p = 'Wow you are the smartest person I have ever met.'
    q = 'Wow you are probably the best tic-tac-toe player in the world.'
    r = 'You are not the brightest bulb in the box.'
    s = 'You should be proud of yourself.'
    t = 'You are doing great! Just kidding.'
    shitList = [a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t]
    print(f'Computer says, "{shitList[random.randint(0,19)]}"\n')

def gameTitle():
    print("""\n
  _   _        _               _             
 | | (_)      | |             | |            
 | |_ _  ___  | |_ __ _  ___  | |_ ___   ___ 
 | __| |/ __| | __/ _` |/ __| | __/ _ \ / _ \\
 | |_| | (__  | || (_| | (__  | || (_) |  __/
  \__|_|\___|  \__\__,_|\___|  \__\___/ \___|\n\n""")

def computerWinsGame():
    print("""\n\n
                                  _                       _           
                                 | |                     (_)          
   ___ ___  _ __ ___  _ __  _   _| |_ ___ _ __  __      ___ _ __  ___ 
  / __/ _ \| '_ ` _ \| '_ \| | | | __/ _ \ '__| \ \ /\ / / | '_ \/ __|
 | (_| (_) | | | | | | |_) | |_| | ||  __/ |     \ V  V /| | | | \__ \\
  \___\___/|_| |_| |_| .__/ \__,_|\__\___|_|      \_/\_/ |_|_| |_|___/
                     | |                                              
                     |_|                                              
    \n\n""")

def playerWinsGame():
    print("""\n\n
        _                                  _           
       | |                                (_)          
  _ __ | | __ _ _   _  ___ _ __  __      ___ _ __  ___ 
 | '_ \| |/ _` | | | |/ _ \ '__| \ \ /\ / / | '_ \/ __|
 | |_) | | (_| | |_| |  __/ |     \ V  V /| | | | \__ \\
 | .__/|_|\__,_|\__, |\___|_|      \_/\_/ |_|_| |_|___/
 | |             __/ |                                 
 |_|            |___/                                  
    \n\n""")

def drawMsg():
    print("""\n\n
      _                    
     | |                   
   __| |_ __ __ ___      __
  / _` | '__/ _` \ \ /\ / /
 | (_| | | | (_| |\ V  V / 
  \__,_|_|  \__,_| \_/\_/  
    \n\n""")

def computerWinsMatch():
    print("""
                                  _                       _                             _       _     
                                 | |                     (_)                           | |     | |    
   ___ ___  _ __ ___  _ __  _   _| |_ ___ _ __  __      ___ _ __  ___   _ __ ___   __ _| |_ ___| |__  
  / __/ _ \| '_ ` _ \| '_ \| | | | __/ _ \ '__| \ \ /\ / / | '_ \/ __| | '_ ` _ \ / _` | __/ __| '_ \ 
 | (_| (_) | | | | | | |_) | |_| | ||  __/ |     \ V  V /| | | | \__ \ | | | | | | (_| | || (__| | | |
  \___\___/|_| |_| |_| .__/ \__,_|\__\___|_|      \_/\_/ |_|_| |_|___/ |_| |_| |_|\__,_|\__\___|_| |_|
                     | |                                                                              
                     |_|                                                                              
    \n""")

def playerWinsMatch():
    print("""
        _                                  _                             _       _     
       | |                                (_)                           | |     | |    
  _ __ | | __ _ _   _  ___ _ __  __      ___ _ __  ___   _ __ ___   __ _| |_ ___| |__  
 | '_ \| |/ _` | | | |/ _ \ '__| \ \ /\ / / | '_ \/ __| | '_ ` _ \ / _` | __/ __| '_ \ 
 | |_) | | (_| | |_| |  __/ |     \ V  V /| | | | \__ \ | | | | | | (_| | || (__| | | |
 | .__/|_|\__,_|\__, |\___|_|      \_/\_/ |_|_| |_|___/ |_| |_| |_|\__,_|\__\___|_| |_|
 | |             __/ |                                                                 
 |_|            |___/                                                                  
    \n""")

def msgWarning():
    print("""
                           _             
 __      ____ _ _ __ _ __ (_)_ __   __ _ 
 \ \ /\ / / _` | '__| '_ \| | '_ \ / _` |
  \ V  V / (_| | |  | | | | | | | | (_| |
   \_/\_/ \__,_|_|  |_| |_|_|_| |_|\__, |
                                   |___/ 
    """)

def msgThree():
    print("""
 333333333333333   
3:::::::::::::::33 
3::::::33333::::::3
3333333     3:::::3
            3:::::3
            3:::::3
    33333333:::::3 
    3:::::::::::3  
    33333333:::::3 
            3:::::3
            3:::::3
            3:::::3
3333333     3:::::3
3::::::33333::::::3
3:::::::::::::::33 
 333333333333333  
    """)

def msgTwo():
    print("""
 222222222222222    
2:::::::::::::::22  
2::::::222222:::::2 
2222222     2:::::2 
            2:::::2 
            2:::::2 
         2222::::2  
    22222::::::22   
  22::::::::222     
 2:::::22222        
2:::::2             
2:::::2             
2:::::2       222222
2::::::2222222:::::2
2::::::::::::::::::2
22222222222222222222
    """)

def msgOne():
    print("""
  1111111   
 1::::::1   
1:::::::1   
111:::::1   
   1::::1   
   1::::1   
   1::::1   
   1::::l   
   1::::l   
   1::::l   
   1::::l   
   1::::l   
111::::::111
1::::::::::1
1::::::::::1
111111111111
    """)

def ackbar():
    print("""\
                            __...------------._
                         ,-'                   `-.
                      ,-'                         `.
                    ,'                            ,-`.
                   ;                              `-' `.
                  ;                                 .-. \\
                 ;                           .-.    `-'  \\
                ;                            `-'          \\
               ;                                          `.
               ;                                           :
              ;                                            |
             ;                                             ;
            ;                            ___              ;
           ;                        ,-;-','.`.__          |
       _..;                      ,-' ;`,'.`,'.--`.        |
      ///;           ,-'   `. ,-'   ;` ;`,','_.--=:      /
     |'':          ,'        :     ;` ;,;,,-'_.-._`.   ,'
     '  :         ;_.-.      `.    :' ;;;'.ee.    \|  /
      \.'    _..-'/8o. `.     :    :! ' ':8888)   || /
       ||`-''    \\88o\ :     :    :! :  :`""'    ;;/
       ||         \"88o\;     `.    \ `. `.      ;,'
       /)   ___    `."'/(--.._ `.    `.`.  `-..-' ;--.
       \(.=\"\"\"\"\"==.. `'-'     `.|      `-`-..__.-' `. `.
        |          `"==.__      )                    )  ;
        |   ||           `"=== '                   .'  .'
        /\,,||||  | |           \                .'   .'
        | |||'|' |'|'           \|             .'   _.' \\
        | |\' |  |           || ||           .'    .'    \\
        ' | \ ' |'  .   ``-- `| ||         .'    .'       \\
          '  |  ' |  .    ``-.._ |  ;    .'    .'          `.
       _.--,;`.       .  --  ...._,'   .'    .'              `.__
     ,'  ,';   `.     .   --..__..--'.'    .'                __/_\\
   ,'   ; ;     |    .   --..__.._.'     .'                ,'     `.
  /    ; :     ;     .    -.. _.'     _.'                 /         `
 /     :  `-._ |    .    _.--'     _.'                   |
/       `.    `--....--''       _.'                      |
          `._              _..-'                         |
             `-..____...-''                              |
                                                         |
                                                         |

  ___ _____ _ ____       _      _____ ____      _    ____  _ 
 |_ _|_   _( ) ___|     / \    |_   _|  _ \    / \  |  _ \| |
  | |  | | |/\___ \    / _ \     | | | |_) |  / _ \ | |_) | |
  | |  | |    ___) |  / ___ \    | | |  _ <  / ___ \|  __/|_|
 |___| |_|   |____/  /_/   \_\   |_| |_| \_\/_/   \_\_|   (_)                                                         
""")

main()