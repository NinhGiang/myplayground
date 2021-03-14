
import pygame, sys, random
from pygame.locals import *

# Tạo các biến cần thiết
BOARDWIDTH = 4  # số cột của board
BOARDHEIGHT = 4 # số hàng của board
TILESIZE = 80 # size của 1 ô
WINDOWWIDTH = 640 # độ dài cửa sổ
WINDOWHEIGHT = 480 # độ rộng cửa sổ
FPS = 30
BLANK = None

# Màu
#                 R    G    B
BLACK =         (  0,   0,   0)
WHITE =         (255, 255, 255)
BRIGHTBLUE =    (  0,  50, 255)
DARKTURQUOISE = (  3,  54,  73)
GREEN =         (  0, 204,   0)

# Thiết lập màu cho các thành phần
BGCOLOR = DARKTURQUOISE # màu background
TILECOLOR = GREEN # màu ô
TEXTCOLOR = WHITE # màu chữ
BORDERCOLOR = BRIGHTBLUE # màu viền
BASICFONTSIZE = 20 # cỡ chữ

BUTTONCOLOR = WHITE # màu của nút
BUTTONTEXTCOLOR = BLACK # màu chữ của nút
MESSAGECOLOR = WHITE # màu message

XMARGIN = int((WINDOWWIDTH - (TILESIZE * BOARDWIDTH + (BOARDWIDTH - 1))) / 2)
YMARGIN = int((WINDOWHEIGHT - (TILESIZE * BOARDHEIGHT + (BOARDHEIGHT - 1))) / 2)

UP = 'up'
DOWN = 'down'
LEFT = 'left'
RIGHT = 'right'

def main():
    # gọi biến global
    # ...
    pygame.init()
    # tạo đồng hồ, màn hình game, đặt caption, chọn font
    # ...

    # Tạo các nút reset, màn hình mới, giải màn hình
    # ...

    mainBoard, solutionSeq = generateNewPuzzle(80)
    SOLVEDBOARD = getStartingBoard() # một màn hình đã giải thì sẽ giống hệt màn hình ban đầu.
    allMoves = [] # danh sách lưu các bước đi 

    while True: # main game loop
        slideTo = None # biến lưu giữ hướng mà ô cần đi
        msg = 'Nhan vao o hoac dung phim mui ten de di chuyen' # masg hướng dẫn chơi, đạt ở góc trên bên trái.
        if mainBoard == SOLVEDBOARD:
            msg = 'Da giai xong!'

        drawBoard(mainBoard, msg)
        # gọi hàm kiểm tra đóng chương trình
        checkForQuit()
        # điều khiển các nút nhấn và click chuột
        for event in pygame.event.get(): # event handling loop
            if event.type == MOUSEBUTTONUP:
                spotx, spoty = getSpotClicked(mainBoard, event.pos[0], event.pos[1])

            # ...

            elif event.type == KEYUP: # kiểm tra các phím
                if event.key in (K_LEFT, K_a) and isValidMove(mainBoard, LEFT):
                    slideTo = LEFT
                elif event.key in (K_RIGHT, K_d) and isValidMove(mainBoard, RIGHT):
                    slideTo = RIGHT
                elif event.key in (K_UP, K_w) and isValidMove(mainBoard, UP):
                    slideTo = UP
                elif event.key in (K_DOWN, K_s) and isValidMove(mainBoard, DOWN):
                    slideTo = DOWN

        if slideTo:
            slideAnimation(mainBoard, slideTo, 'Click tile or press arrow keys to slide.', 8) # show slide on screen
            makeMove(mainBoard, slideTo)
            allMoves.append(slideTo) # record the slide
        pygame.display.update()
        FPSCLOCK.tick(FPS)


def terminate():
    pygame.quit()
    sys.exit()

# hàm kiểm tra tắt màn hình
# ...


def getStartingBoard():
    # Return a board data structure with tiles in the solved state.
    # For example, if BOARDWIDTH and BOARDHEIGHT are both 3, this function
    # returns [[1, 4, 7], [2, 5, 8], [3, 6, BLANK]]
    counter = 1
    board = []
    for x in range(BOARDWIDTH):
        column = []
        for y in range(BOARDHEIGHT):
            column.append(counter)
            counter += BOARDWIDTH
        board.append(column)
        counter -= BOARDWIDTH * (BOARDHEIGHT - 1) + BOARDWIDTH - 1

    board[BOARDWIDTH-1][BOARDHEIGHT-1] = BLANK
    return board


def getBlankPosition(board):
    # Return the x and y of board coordinates of the blank space.
    for x in range(BOARDWIDTH):
        for y in range(BOARDHEIGHT):
            if board[x][y] == BLANK:
                return (x, y)

# hàm di chuyển
# ...

# hàm kiểm tra xem ô có di chuyển đc không


# hàm xáo trộn ngẫu nhiên
def getRandomMove(board, lastMove=None):
    # tạo danh sách các loại di chuyển
    validMoves = [UP, DOWN, LEFT, RIGHT]

    # xóa những di chuyển không thể thực hiện
    if lastMove == UP or not isValidMove(board, DOWN):
        validMoves.remove(DOWN)
    if lastMove == DOWN or not isValidMove(board, UP):
        validMoves.remove(UP)
    if lastMove == LEFT or not isValidMove(board, RIGHT):
        validMoves.remove(RIGHT)
    if lastMove == RIGHT or not isValidMove(board, LEFT):
        validMoves.remove(LEFT)

    # chọn một di chuyển hợp lệ bất kì
    return random.choice(validMoves)


# hàm lấy tọa độ của ô từ vị trí
# ...

# hàm lấy vị trí từ con trỏ chuột
# ...

# hàm vẽ các ô
def drawTile(tilex, tiley, number, adjx=0, adjy=0):
    # draw a tile at board coordinates tilex and tiley, optionally a few
    # pixels over (determined by adjx and adjy)
    left, top = getLeftTopOfTile(tilex, tiley)
    pygame.draw.rect(SCREEN, TILECOLOR, (left + adjx, top + adjy, TILESIZE, TILESIZE))
    textSurf = BASICFONT.render(str(number), True, TEXTCOLOR)
    textRect = textSurf.get_rect()
    textRect.center = left + int(TILESIZE / 2) + adjx, top + int(TILESIZE / 2) + adjy
    SCREEN.blit(textSurf, textRect)

# hame makeText
def makeText(text, color, bgcolor, top, left):
    # create the Surface and Rect objects for some text.
    textSurf = BASICFONT.render(text, True, color, bgcolor)
    textRect = textSurf.get_rect()
    textRect.topleft = (top, left)
    return (textSurf, textRect)

# hàm vẽ board
def drawBoard(board, message):
    # tô màn hình
    SCREEN.fill(BGCOLOR)
    # nếu có message thì in message ra
    if message:
        textSurf, textRect = makeText(message, MESSAGECOLOR, BGCOLOR, 5, 5)
        SCREEN.blit(textSurf, textRect)
    # in các ô ra
    for tilex in range(len(board)):
        for tiley in range(len(board[0])):
            if board[tilex][tiley]:
                drawTile(tilex, tiley, board[tilex][tiley])

    # vẽ viền vuông
    left, top = getLeftTopOfTile(0, 0)
    width = BOARDWIDTH * TILESIZE
    height = BOARDHEIGHT * TILESIZE
    pygame.draw.rect(SCREEN, BORDERCOLOR, (left - 5, top - 5, width + 11, height + 11), 4)
    # in các button ra
    SCREEN.blit(RESET_SURF, RESET_RECT)
    SCREEN.blit(NEW_SURF, NEW_RECT)
    SCREEN.blit(SOLVE_SURF, SOLVE_RECT)

# hàm di chuyển bằng mắt
def slideAnimation(board, direction, message, animationSpeed):
    # movex, movey là biến chứa vị trí mới của ô trống
    blankx, blanky = getBlankPosition(board)
    if direction == UP:
        movex = blankx
        movey = blanky + 1
    elif direction == DOWN:
        movex = blankx
        movey = blanky - 1
    elif direction == LEFT:
        movex = blankx + 1
        movey = blanky
    elif direction == RIGHT:
        movex = blankx - 1
        movey = blanky

    # vẽ lại board (không có ô trống)
    drawBoard(board, message)
    baseSurf = SCREEN.copy()
    # vẽ ô trống lên
    # lấy tọa độ từ vị trí
    moveLeft, moveTop = getLeftTopOfTile(movex, movey)
    pygame.draw.rect(baseSurf, BGCOLOR, (moveLeft, moveTop, TILESIZE, TILESIZE))

    for i in range(0, TILESIZE, animationSpeed):
        # làm hiệu ứng di chuyển
        checkForQuit()
        SCREEN.blit(baseSurf, (0, 0))
        if direction == UP:
            drawTile(movex, movey, board[movex][movey], 0, -i)
        if direction == DOWN:
            drawTile(movex, movey, board[movex][movey], 0, i)
        if direction == LEFT:
            drawTile(movex, movey, board[movex][movey], -i, 0)
        if direction == RIGHT:
            drawTile(movex, movey, board[movex][movey], i, 0)

        pygame.display.update()
        FPSCLOCK.tick(FPS)

# hàm ra đề mới
def generateNewPuzzle(numSlides):
    # From a starting configuration, make numSlides number of moves (and
    # animate these moves).
    sequence = []
    board = getStartingBoard()
    drawBoard(board, '')
    pygame.display.update()
    pygame.time.wait(500) # pause 500 milliseconds for effect
    lastMove = None
    for i in range(numSlides):
        move = getRandomMove(board, lastMove)
        slideAnimation(board, move, 'Generating new puzzle...', animationSpeed=int(TILESIZE / 3))
        makeMove(board, move)
        sequence.append(move)
        lastMove = move
    return (board, sequence)

# hàm reset lại (thực hiện ngược lại các thao tác đã làm)
def resetAnimation(board, allMoves):
    # make all of the moves in allMoves in reverse.
    revAllMoves = allMoves[:] # gets a copy of the list
    revAllMoves.reverse()

    for move in revAllMoves:
        if move == UP:
            oppositeMove = DOWN
        elif move == DOWN:
            oppositeMove = UP
        elif move == RIGHT:
            oppositeMove = LEFT
        elif move == LEFT:
            oppositeMove = RIGHT
        slideAnimation(board, oppositeMove, '', animationSpeed=int(TILESIZE / 2))
        makeMove(board, oppositeMove)


if __name__ == '__main__':
    main()