/*
68. Text Justification

Given an array of words and a length L, format the text such that each
line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many
words as you can in each line. Pad extra spaces ' ' when necessary so
that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line do not divide evenly between words, the
empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space
is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/
public class Solution {
	public List<String> fullJustify(String[] words, int maxWidth) {
		ArrayList<String> res = new ArrayList<String>();
		// corner case
		if (words == null || words.length == 0) {
			return res;
		}
		int first = 0; // 一行中第一个单词的位置
		int last = 0;  // 一行中最后一个单词的位置
		int count = 0; // 一行中的字符数(不包括空格)
		for (last = 0; last < words.length; last++) {
			// last - first表示单词间隔数,因为单词之间至少一个空格
			if ((count + words[last].length() + last - first) > maxWidth) {
				last--; // 最后一个单词不满足
				int konggenum = maxWidth - count;
				// 判断空格是否可以被间隔均分
				int isjunfen = 0;
				int countkongge = 0;
				if ((last - first) > 0) {
					isjunfen = konggenum % (last - first);
					countkongge = konggenum / (last - first);
				}
				StringBuilder row = new StringBuilder();
				for (int j = first; j <= last; j++) {
					row.append(words[j]);
					if (j < last) {
						for (int i = 0; i < countkongge; i++) {
							row.append(" ");
						}
						// 不可以均分 ，多出的空格依次放在左边
						if (isjunfen > 0) {
							row.append(" ");
						}
						isjunfen--;
					}

				}
				for (int j = row.length(); j < maxWidth; j++) {
					row.append(" ");
				}
				res.add(row.toString());
				first = last + 1;
				count = 0;
			}
			else {
				count += words[last].length();
			}
		}
		//单独处理最后一行，因为最后一行的空格分布与前面不一样
		StringBuilder row = new StringBuilder();
		for (int i = first; i < words.length; i++) {
			row.append(words[i]);
			if (row.length() < maxWidth) {
				row.append(" ");
			}
		}
		for (int i = row.length(); i < maxWidth; i++) {
			row.append(" ");
		}
		res.add(row.toString());
		return res;
	}
}