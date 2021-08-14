#include <ncurses.h>
#include <string>
using namespace std;

int main(int argc, char** argv)
{
	/* NCURSES START */
	initscr();
	cbreak();
	noecho();

	int height, width, start_y, start_x;
	height = 10;
	width = 20;
	start_y = start_x = 10;

	WINDOW * win = newwin(height, width, start_y, start_x);
	refresh();

	char c = '+';
	char space = ' ';

//	box(win, 103, 103);
	int left, right, top, bottom, tlc, trc, blc, brc;
	left = right = 103;
	top = 104;
	bottom = (int)space;
	tlc = trc = blc = brc = (int)c;

	wborder(win, left, right, top, bottom, tlc, trc, blc, brc);
	mvwprintw(win, 1, 1, "this is my box");
	wrefresh(win);

	getch();
	getch();

	endwin();
	/* NCURSES END */

	return 0;
}
