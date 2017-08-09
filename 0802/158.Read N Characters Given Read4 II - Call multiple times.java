/*
158. Read N Characters Given Read4 II - Call multiple times

The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. 
For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that
reads n characters from the file.

Note:
The read function may be called multiple times.
*/
/* The read4 API is defined in the parent class Reader4.
	  int read4(char[] buf); */
public class Solution extends Reader4 {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */

	private int offSet = 0;
	private int remaining = 0;
	private boolean isEndOfFile = false;
	private char[] buffer = new char[4];
	
	public int read(char[] buf, int n) {
		int readBytes = 0;
		while (readBytes < n && (remaining != 0 || !isEndOfFile)) {
			int readSize = 0;
			if (remaining != 0) {
				readSize = remaining;
			} else {
				offSet = 0;
				readSize = read4(buffer);
				if (readSize != 4) {
					isEndOfFile = true;
				}
			}
			int length = Math.min(n - readBytes, readSize);
			for (int i= offSet; i<offSet + length; i++) {
				buf[readBytes++] = buffer[i];
			}
			remaining = readSize - length;
			if (remaining != 0) {
				offSet += length;
			}
		}
		return readBytes;
	}
}