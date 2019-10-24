import random
import time

def main():
    playGame()

# LOOP THROUGH HOW MANY TIMES?
def playGame():
    gameCount = 0
    compWin = 0
    playerWin = 0
    flag = -1
    while flag == -1:
        print(f'\n------------------\n[[ GAME {gameCount} ]]\n------------------\n')
        nextTurn = random.randrange(1, 3, 1)
        board = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0}
        drawBoard(board)
        turnCount = 1
        while nextTurn == 1 or nextTurn == 2:
            if nextTurn == 1:
                print(f'\nTURN {turnCount}\n-------')
                nextTurn = compTurnPlayer(board)
                turnCount += 1
            elif nextTurn == 2:
                print(f'\nTURN {turnCount}\n-------')
                nextTurn = compTurn(board)
                turnCount += 1
        gameCount += 1
        if nextTurn == -1:
            compWin += 1
        elif nextTurn == -2:
            playerWin += 1

        if gameCount == 10000:
            flag = 1
            print('\nGAME PLAY MODE: EASY')
            print(f'Total Games: {gameCount}')
            print(f'Comp Player wins: {playerWin}')
            print(f'Comp Wins: {compWin}\n')

# Draws the graphical version of the current board state for the player to see
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
    print(f'\n\n{draw}')

# Creates a nested list of all possible winning board position patterns - used for computer decision making process
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

# Creates nested list of mathematical sums of all possible winning board position patterns - used in endGame() function.
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

# Determines if game continues and which players turn next, or if ends and who wins or loses, or if a draw.
# If impossible mode, then computer will always win no matter what outcome.
def endGame(board, player):
    winConList = winConListFunct(board)
    for i in winConList:
        if i == -3:
            return(-2)
        elif i == 3:
            return(-1)
    countOpen = 0
    for i in range(1, 10, 1):
        if board[i] == 0:
            countOpen += 1
    if countOpen != 0:
        return(player)
    else:
        return(0)

def compTurnPlayer(board):
    results = decisionEasyPlayer(board)
    board = results[1]
    move = list(results[2].keys())
    drawBoard(board)
    print(f'\nComputer Player chose square {move[0]}\n')
    return(endGame(board, 2))

def compTurn(board):
    results = decisionHard(board)
    board = results[1]
    move = list(results[2].keys())
    drawBoard(board)
    print(f'\nComputer chose square {move[0]}\n')
    return(endGame(board, 1))

def decisionEasyPlayer(board):
    winConList = winConListFunct(board)
    winSquaresList = winSquaresListFunct(board)
    occupiedSquares = 0
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1

    # 1) TURN 1: A) Randomly decide where to go first
    if occupiedSquares == 0:
        move = random.randrange(1, 10, 1)
        choiceDict = {move:-1}
        board.update(choiceDict)
        return({1:board, 2:choiceDict})

    # 2) First, always evaluate if can win this turn and choose that square to win
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:-1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 3) Second, always evaluate if can lose this turn and choose that square to prevent loss
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:-1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 4) Last, choose square if all other above conditions not met
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:-1}
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

def decisionHard(board):
    winConList = winConListFunct(board)
    winSquaresList = winSquaresListFunct(board)
    choiceDict = False
    occupiedSquares = 0
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1

    # 1) TURN 1: A) If go first, randomly decide which aggressive two-turn setup guaranteed win strategy to initiate
    if occupiedSquares == 0:
        strategy = random.randrange(0, 3, 1)
        # A) Middle strategy
        if strategy == 1:
            choiceDict = {5:1}
        # B) Corner strategy
        elif strategy == 2:
            choiceDict = {7:1}
        # C) Side strategy
        else:
            choiceDict = {4:1}
        board.update(choiceDict)
        return({1:board, 2:choiceDict})

    # 2) TURN 2: Already can successfully defend and eliminate one potential aggressive two-turn setup guaranteed loss strategy here
    if occupiedSquares == 1:
        if board[5] == 0:
            choiceDict = {5:1}
        elif board[5] == -1:
            choiceDict = {3:1}

        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

    # 3) First, always evaluate if can win this turn and choose that square to win
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 4) Second, always evaluate if can lose this turn and choose that square to prevent loss
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return({1:board, 2:choiceDict})

    # 5) TURN 3: can be more aggressive. Phase 1 of aggressive two-turn setup guaranteed win strategies here, phase 2 in TURN 5.
    # A) 1. Middle strategy section one
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
            # A) 2. Middle strategy section two
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

        # B) 1. Corner strategy
        if board[7] == 1:
            if board[1] == -1:
                choiceDict = {8:1}
            elif board[4] == -1 or board[3] == -1 or board[6] == -1:
                choiceDict = {9:1}
            elif board[9] == -1 or board[8] == -1 or board[2] == -1:
                choiceDict = {1:1}
            else:
                choiceDict = {3:1}

            board.update(choiceDict)
            return({1:board, 2:choiceDict})

        # C) 1. Side strategy section one
        if board[4] == 1:
            if board[5] == -1:
                choiceDict = {2:1}
            elif board[6] == -1:
                choiceDict = {5:1}
            # C) 2. Side strategy section two
            elif board[3] == -1 or board[2] == -1:
                choiceDict = {9:1}
            # C) 3. Side strategy section three
            elif board[9] == -1 or board[8] == -1:
                choiceDict = {2:1}
            # C) 4. Side strategy CHRISTIAN'S FACEMELT 
            elif board[7] == -1:
                choiceDict = {3:1}
            else:
                choiceDict = {9:1}

            board.update(choiceDict)
            return({1:board, 2:choiceDict})

    # 6) TURN 4: must be careful, defend and prevent aggressive two-turn setup guaranteed loss strategies here
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

    # 7) TURN 5: COMPLETE aggressive two-turn setup guaranteed win strategies
    # A)
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
            elif board[8] == -1 and board[2] == 1:
                if board[1] == -1 or board[3] == -1:
                    choiceDict = {7:1}
            
            # G
            elif board[1] == 1:
                if board[2] == -1:
                    choiceDict = {4:1}
                if board[4] == -1:
                    choiceDict = {2:1}
            elif board[3] == 1:
                if board[2] == -1:
                    choiceDict = {6:1}
                elif board[6] == -1:
                    choiceDict = {2:1}
            elif board[9] == 1:
                if board[8] == -1:
                    choiceDict = {6:1}
                elif board[6] == -1:
                    choiceDict = {8:1}
            elif board[7] == 1:
                if board[8] == -1:
                    choiceDict = {4:1}
                elif board[4] == -1:
                    choiceDict = {8:1}
            
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

        # C CORNER STRATEGY
        if board[7] == 1:
            if board[9] == 1:
                if board[4] == -1 or board[6] == -1:
                    choiceDict = {5:1}
                elif board[3] == -1:
                    choiceDict = {1:1}
            elif board[1] == 1:
                if board[9] == -1:
                    choiceDict = {3:1}
                elif board[8] == -1 or board[2] == -1:
                    choiceDict = {5:1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

            # C MIDDLE STRATEGY
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
            elif board[6] == -1:
                if board[7] == -1 or board[1] == -1:
                    choiceDict = {9:1}

            if choiceDict != False:
                board.update(choiceDict)
                return({1:board, 2:choiceDict})

    # 8) TURN 7: One case where need to complete aggressive guaranteed win strategy
    if occupiedSquares == 6:
        if board[4] == 1 and board[3] == 1 and board[8] == 1 and board[7] == -1 and board[9] == -1:
            if board[6] == -1:
                choiceDict = {2:1}
            elif board[1] == -1:
                choiceDict = {5:1}
        
        if choiceDict != False:
            board.update(choiceDict)
            return({1:board, 2:choiceDict})
            
    # 9) Last, choose square if all other above conditions not met. This method also always automatically executes an
    # aggressive two-turn setup guaranteed win strategy, in combination with decision trees above.
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return({1:board, 2:choiceDict})

# Initiate program
main()