# import thư viện
import pygame, sys, time
from pygame.locals import *
from random import *

# khai báo màu cho 12 số
BLACK = (0, 0, 0)
RED = (244, 67, 54)
PINK = (234, 30, 99)
PURPLE = (156, 39, 176)
DEEP_PURPLE = (103, 58, 183)
BLUE = (33, 150, 243)
TEAL = (0, 150, 136)
L_GREEN = (139, 195, 74)
GREEN = (60, 175, 80)
ORANGE = (255, 152, 0)
DEEP_ORANGE = (255, 87, 34)
BROWN = (121, 85, 72)

# tạo dictionary bắt cặp một số với một màu
colour_dict = { 0:BLACK, 2:RED, 4:PINK, 8:PURPLE, 16:DEEP_PURPLE, 32:BLUE, 64:TEAL, 128:L_GREEN, 256:GREEN, 512:ORANGE, 1024: DEEP_ORANGE, 2048:BROWN}

# khai báo các hằng số trong game
TOTAL_POINTS = 0
BOARD_SIZE = 4

# khởi tạo màn hình game
pygame.init()
SURFACE = pygame.display.set_mode((400, 500), 0, 32)
pygame.display.set_caption("2048")

# hai báo font chứ cho điểm và hướng dẫn khác
myfont = pygame.font.SysFont("monospace", 25)
scorefont = pygame.font.SysFont("monospace", 50)

# khai báo ma trận chứa giá trị của các ô
tileMatrix = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]

# viết hàm main
def main():
	# tạo 2 ô số 2 ở vị trí bất kì
	placeRandomTile()
	placeRandomTile()
	# in ma trận ra
	printMatrix()
	run = True
	while run:
		for event in pygame.event.get():
			# kiểm tra nút tắt màn hình
			if event.type == QUIT:
				run = False
				pygame.quit()
				system.exit()
			# kiểm tra liệu có khả năng đi tiếp không, nếu có:
			if checkIfCanGo() == True:
				if event.type == KEYDOWN:
					# nếu là một trong bốn phím arrow
					if isArrow(event.key):
						# xác định sẽ xoay bảng bao nhiêu lần và xoay
						rotations = getRotations(event.key)
						for i in range(0, rotations):
							rotateMatrixClockwise()
						# nếu như ô có thể di chuyển thì 
						# di chuyển (move), 
						# gộp lại (merge), 
						# tạo 1 ô mới 
						if canMove():
							moveTiles()
							mergeTiles()
							placeRandomTile()
						# xoay bảng lại như cũ
						for j in range(0, (4 - rotations) % 4):
							rotateMatrixClockwise()
						# in bảng
						printMatrix()
			# nếu không thể di chuyển:
			else:
				printGameOver()
			# nếu nhấn nút R thì reset màn hình
			if event.type == KEYDOWN:
				if event.key == pygame.K_r:
					reset()
		# cập nhật màn hình game
		pygame.display.update()

# hàm lấy màu theo giá trị số của ô
def getColour(i):
	return colour_dict[i]

# hàm in ma trận
# ...

# in màn hình khi thua
# ...

# hàm đặt một ô số 2 lên vị trí bất kì
def placeRandomTile():
	# chọn một số k ngẫu nhiên từ 0 đến 15
	k = floor(random() * BOARD_SIZE * BOARD_SIZE)
	# xét xem ô tương ứng đó đã có số chưa, và thay đổi cho đến khi gặp ô số 0 
	while tileMatrix[floor(k / BOARD_SIZE)][k % BOARD_SIZE] != 0:
		k = floor(random() * BOARD_SIZE * BOARD_SIZE)
	# đặt ô đó bằng 2
	tileMatrix[floor(k / BOARD_SIZE)][k % BOARD_SIZE] = 2

# hàm làm tròn số
def floor(n):
	return int(n - (n % 1))

# hàm di chuyển số giữa các ô
# ...

# hàm ghép số lại
# ...

# hàm kiểm tra có thực hiện được bước đi nào không
# ...

# hàm reset game
# ...

# hàm kiểm tra xem có bất kì ô trống nào để di chuyển qua trái hoặc 2 ô kề bằng nhau
# hàm này được chạy sau khi nhấn phím mũi tên
# ...

# hàm xoay bảng theo chiều kim đồng hồ
def rotateMatrixClockwise():
	for i in range(0, int(BOARD_SIZE/2)):
		for k in range(i, BOARD_SIZE- i - 1):
			temp1 = tileMatrix[i][k]
			temp2 = tileMatrix[BOARD_SIZE - 1 - k][i]
			temp3 = tileMatrix[BOARD_SIZE - 1 - i][BOARD_SIZE - 1 - k]
			temp4 = tileMatrix[k][BOARD_SIZE - 1 - i]

			tileMatrix[BOARD_SIZE - 1 - k][i] = temp1
			tileMatrix[BOARD_SIZE - 1 - i][BOARD_SIZE - 1 - k] = temp2
			tileMatrix[k][BOARD_SIZE - 1 - i] = temp3
			tileMatrix[i][k] = temp4

# hàm kiểm tra phím nhấn có phải là phím mũi tên không
def isArrow(k):
	return(k == pygame.K_UP or k == pygame.K_DOWN or k == pygame.K_LEFT or k == pygame.K_RIGHT)

# hàm xác định số lần xoay màn hình
def getRotations(k):
	if k == pygame.K_UP:
		return 0
	elif k == pygame.K_DOWN:
		return 2
	elif k == pygame.K_LEFT:
		return 1
	elif k == pygame.K_RIGHT:
		return 3
		
# chạy hàm main
main()