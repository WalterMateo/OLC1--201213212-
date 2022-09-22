package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
 
    yyline = 1; 
    yychar = 1; 
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NOT_ACCEPT,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NOT_ACCEPT,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NOT_ACCEPT,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NOT_ACCEPT,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NOT_ACCEPT,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NOT_ACCEPT,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NOT_ACCEPT,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NOT_ACCEPT,
		/* 207 */ YY_NOT_ACCEPT,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NOT_ACCEPT,
		/* 210 */ YY_NOT_ACCEPT,
		/* 211 */ YY_NOT_ACCEPT,
		/* 212 */ YY_NOT_ACCEPT,
		/* 213 */ YY_NOT_ACCEPT,
		/* 214 */ YY_NOT_ACCEPT,
		/* 215 */ YY_NOT_ACCEPT,
		/* 216 */ YY_NOT_ACCEPT,
		/* 217 */ YY_NOT_ACCEPT,
		/* 218 */ YY_NOT_ACCEPT,
		/* 219 */ YY_NOT_ACCEPT,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NOT_ACCEPT,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NOT_ACCEPT,
		/* 224 */ YY_NOT_ACCEPT,
		/* 225 */ YY_NOT_ACCEPT,
		/* 226 */ YY_NOT_ACCEPT,
		/* 227 */ YY_NOT_ACCEPT,
		/* 228 */ YY_NOT_ACCEPT,
		/* 229 */ YY_NOT_ACCEPT,
		/* 230 */ YY_NOT_ACCEPT,
		/* 231 */ YY_NOT_ACCEPT,
		/* 232 */ YY_NOT_ACCEPT,
		/* 233 */ YY_NOT_ACCEPT,
		/* 234 */ YY_NOT_ACCEPT,
		/* 235 */ YY_NOT_ACCEPT,
		/* 236 */ YY_NOT_ACCEPT,
		/* 237 */ YY_NOT_ACCEPT,
		/* 238 */ YY_NOT_ACCEPT,
		/* 239 */ YY_NOT_ACCEPT,
		/* 240 */ YY_NOT_ACCEPT,
		/* 241 */ YY_NOT_ACCEPT,
		/* 242 */ YY_NOT_ACCEPT,
		/* 243 */ YY_NOT_ACCEPT,
		/* 244 */ YY_NOT_ACCEPT,
		/* 245 */ YY_NOT_ACCEPT,
		/* 246 */ YY_NOT_ACCEPT,
		/* 247 */ YY_NOT_ACCEPT,
		/* 248 */ YY_NOT_ACCEPT,
		/* 249 */ YY_NOT_ACCEPT,
		/* 250 */ YY_NOT_ACCEPT,
		/* 251 */ YY_NOT_ACCEPT,
		/* 252 */ YY_NOT_ACCEPT,
		/* 253 */ YY_NOT_ACCEPT,
		/* 254 */ YY_NOT_ACCEPT,
		/* 255 */ YY_NOT_ACCEPT,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NOT_ACCEPT,
		/* 258 */ YY_NOT_ACCEPT,
		/* 259 */ YY_NOT_ACCEPT,
		/* 260 */ YY_NOT_ACCEPT,
		/* 261 */ YY_NOT_ACCEPT,
		/* 262 */ YY_NOT_ACCEPT,
		/* 263 */ YY_NOT_ACCEPT,
		/* 264 */ YY_NOT_ACCEPT,
		/* 265 */ YY_NOT_ACCEPT,
		/* 266 */ YY_NOT_ACCEPT,
		/* 267 */ YY_NOT_ACCEPT,
		/* 268 */ YY_NOT_ACCEPT,
		/* 269 */ YY_NOT_ACCEPT,
		/* 270 */ YY_NOT_ACCEPT,
		/* 271 */ YY_NOT_ACCEPT,
		/* 272 */ YY_NOT_ACCEPT,
		/* 273 */ YY_NOT_ACCEPT,
		/* 274 */ YY_NOT_ACCEPT,
		/* 275 */ YY_NOT_ACCEPT,
		/* 276 */ YY_NOT_ACCEPT,
		/* 277 */ YY_NOT_ACCEPT,
		/* 278 */ YY_NOT_ACCEPT,
		/* 279 */ YY_NOT_ACCEPT,
		/* 280 */ YY_NOT_ACCEPT,
		/* 281 */ YY_NOT_ACCEPT,
		/* 282 */ YY_NOT_ACCEPT,
		/* 283 */ YY_NOT_ACCEPT,
		/* 284 */ YY_NOT_ACCEPT,
		/* 285 */ YY_NOT_ACCEPT,
		/* 286 */ YY_NOT_ACCEPT,
		/* 287 */ YY_NOT_ACCEPT,
		/* 288 */ YY_NOT_ACCEPT,
		/* 289 */ YY_NOT_ACCEPT,
		/* 290 */ YY_NOT_ACCEPT,
		/* 291 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"42:9,38,21,42:2,38,42:18,41,42:5,27,42,30,31,33,32,36,25,40,34,39:10,42,29," +
"37,35,26,42:2,8,10,7,9,4,14,20,22,17,24,42,11,3,1,6,16,23,5,15,12,2,13,42:2" +
",18,42:5,19,42,8,10,7,9,4,14,20,22,17,24,42,11,3,1,6,16,23,5,15,12,2,13,42:" +
"2,18,42:2,28,42:65411,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,292,
"0,1,2,3,4,1:10,5,6,1:6,7,1,8,1:3,9,10,1:3,11,1:20,12,1:13,13,1,14,15,16,17," +
"18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42," +
"43,44,45,46,47,48,49,8,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,6" +
"7,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,9" +
"2,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112," +
"113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131" +
",132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,15" +
"0,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,1" +
"69,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187," +
"188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206" +
",207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,22" +
"5,226,227,228,229,230,231,232,233")[0];

	private int yy_nxt[][] = unpackFromString(234,43,
"1,2,70,72,74,76,78,80,82,220,84,70:2,256,86,88,222,90,70:3,3,92,70:2,4,5,94" +
",96,6,7,8,9,10,11,12,13,14,15,16,70,15,70,-1:45,69,-1:3,71,-1:42,105,-1:62," +
"18,-1:54,15,-1:2,15,-1:40,16,107,-1:21,137,-1:51,25,-1:10,25,-1,25,-1:20,24" +
"2,-1:42,291,-1:42,173,-1:42,204,-1:26,257,-1:51,21,-1:34,73,-1,75,-1,77,-1:" +
"8,79,-1:26,224,-1:10,258,-1:31,81,-1:13,83,-1:8,221,-1:27,108,-1:37,85,-1:5" +
"6,272,-1:43,87,-1:27,226,-1:44,89,-1,91,-1:46,109,-1:31,93,-1:60,110,-1:29," +
"97,-1:48,279,-1:3,112,-1:28,99,-1:5,100,-1:8,101,-1:40,113,-1:31,102,-1:12," +
"17,-1:26,114,-1,115,-1:40,103,-1,104,-1:44,116,-1:3,230,-1:41,106,-1:43,22," +
"-1:60,19,-1:22,117,-1:11,227,-1:51,20,-1:20,259,-1:41,118,-1:38,229,-1:52,1" +
"19,-1:32,23,-1:61,228,-1:39,260,-1:2,121,-1:38,233,-1:31,24,-1:44,261,-1:7," +
"122,-1:29,124,-1:44,273,-1,126,-1:45,231,-1:7,127,-1:32,128,-1:39,263,-1:55" +
",26,-1:44,129,-1:29,27,-1:44,130,-1:51,131,-1:34,134,-1:48,136,-1:35,28,-1:" +
"39,238,-1:49,140,-1:35,29,-1:48,143,-1:36,30,-1:38,237,-1:61,236,-1:24,147," +
"-1:53,239,-1:2,265,-1:33,241,-1:38,274,-1:45,150,-1:40,281,-1:46,151,-1:51," +
"152,-1:31,31,-1:39,153,-1:10,154,155,156,-1:27,32,-1:46,33,-1:45,34,-1:40,3" +
"5,-1:42,36,-1:42,37,-1:41,243,-1:43,38,-1:50,245,-1:40,276,-1:31,282,-1:49," +
"39,-1:53,244,-1:32,277,-1:39,167,-1:40,168,-1:12,283,-1:27,169,-1:44,170,-1" +
":12,40,-1:33,246,-1:41,171,-1:41,41,-1:51,172,-1:30,248,-1:43,176,-1:43,42," +
"-1:48,180,-1:36,249,-1:48,43,-1:32,44,-1:42,45,-1:53,270,-1:31,183,-1:61,18" +
"4,-1:39,186,-1:33,187,-1:57,251,-1:38,189,-1:38,46,-1:42,47,-1:38,48,-1:36," +
"49,-1:42,50,-1:43,191,-1:41,51,-1:42,194,-1:44,252,-1:37,197,-1:48,52,-1:42" +
",53,-1:39,54,-1:42,55,-1:54,199,-1:29,253,-1:43,56,-1:40,200,-1:40,287,-1:4" +
"7,57,-1:45,201,-1:45,202,-1:31,58,-1:45,59,-1:58,205,-1:26,254,-1:44,60,-1:" +
"41,207,-1:43,208,-1:37,209,-1:43,255,-1:52,210,-1:38,212,-1:35,61,-1:52,62," +
"-1:35,63,-1:43,215,-1:52,64,-1:38,65,-1:42,66,-1:37,217,-1:41,218,-1:52,67," +
"-1:44,219,-1:31,68,-1:40,95,-1:42,111,-1:44,223,-1,225,-1:46,286,-1:36,123," +
"-1:41,120,-1:38,234,-1:52,132,-1:33,138,-1:47,135,-1:39,235,-1:55,146,-1:34" +
",142,-1:38,266,-1:49,144,-1:31,149,-1:43,267,-1:47,161,-1:39,159,-1:46,163," +
"-1:51,158,-1:37,268,-1:36,174,-1:44,175,-1:41,250,-1:39,269,-1:43,185,-1:38" +
",196,-1:58,188,-1:33,192,-1:40,193,-1:38,198,-1:57,203,-1:26,206,-1:53,211," +
"-1:38,213,-1:38,98,-1:42,262,-1:44,232,-1:47,133,-1:38,240,-1:39,139,-1:43," +
"141,-1:49,275,-1:31,157,-1:49,164,-1:51,160,-1:33,177,-1:38,181,-1:43,190,-" +
"1:43,195,-1:44,214,-1:40,125,-1:41,145,-1:45,165,-1:51,162,-1:33,178,-1:38," +
"182,-1:46,216,-1:40,280,-1:41,148,-1:45,166,-1:42,179,-1:38,247,-1:43,278,-" +
"1:39,271,-1:44,264,-1:50,284,-1:50,285,-1:39,288,-1:44,289,-1:29,290,-1:36");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -3:
						break;
					case 3:
						{yychar=1;}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.MENOS,yyline,yychar, yytext());}
					case -5:
						break;
					case 5:
						{return new Symbol(sym.MAYORSIM,yyline,yychar, yytext());}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.PARIZQ,yyline,yychar, yytext());}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.PARDER,yyline,yychar, yytext());}
					case -9:
						break;
					case 9:
						{return new Symbol(sym.MAS,yyline,yychar, yytext());}
					case -10:
						break;
					case 10:
						{return new Symbol(sym.POR,yyline,yychar, yytext());}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.DIVIDIDO,yyline,yychar, yytext());}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.IGUAL,yyline,yychar, yytext());}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.COMA,yyline,yychar, yytext());}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.MENORSIM,yyline,yychar, yytext());}
					case -15:
						break;
					case 15:
						{}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.ENTERO,yyline,yychar, yytext());}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.SI,yyline,yychar,
                             yytext());}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.ASIGNACION,yyline,yychar,
                             yytext());}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.COMPY,yyline,yychar,
                             yytext());}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.COMPO,yyline,yychar,
                             yytext());}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.NOT,yyline,yychar,
                             yytext());}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.AND,yyline,yychar,
                             yytext());}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.FIN,yyline,yychar,
                             yytext());}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.OR,yyline,yychar,
                             yytext());}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.DECIMAL,yyline,yychar, yytext());}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.OSI,yyline,yychar,
                             yytext());}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.COMO,yyline,yychar,
                             yytext());}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.PARA,yyline,yychar,
                             yytext());}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.MENOR,yyline,yychar,
                             yytext());}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.MAYOR,yyline,yychar,
                             yytext());}
					case -31:
						break;
					case 31:
						{return new Symbol(sym.FALSO,yyline,yychar,
                             yytext());}
					case -32:
						break;
					case 32:
						{return new Symbol(sym.SEGUN,yyline,yychar,
                             yytext());}
					case -33:
						break;
					case 33:
						{return new Symbol(sym.HACER,yyline,yychar,
                             yytext());}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.HASTA,yyline,yychar,
                             yytext());}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.NUMERO,yyline,yychar,
                             yytext());}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.METODO,yyline,yychar,
                             yytext());}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.MODULO,yyline,yychar,
                             yytext());}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.ENTERO,yyline,yychar,
                             yytext());}
					case -39:
						break;
					case 39:
						{return new Symbol(sym.CADENA,yyline,yychar,
                             yytext());}
					case -40:
						break;
					case 40:
						{return new Symbol(sym.FINSI,yyline,yychar,
                             yytext());}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.INICIO,yyline,yychar,
                             yytext());}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.REPETIR,yyline,yychar,
                             yytext());}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.DECIMAL,yyline,yychar,
                             yytext());}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.BOOLEAN,yyline,yychar,
                             yytext());}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.FUNCION,yyline,yychar,
                             yytext());}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.MIENTRAS,yyline,yychar,
                             yytext());}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.ENTONCES,yyline,yychar,
                             yytext());}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.ESIGUAL,yyline,yychar,
                             yytext());}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.EJECUTAR,yyline,yychar,
                             yytext());}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.RETORNAR,yyline,yychar,
                             yytext());}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.CARACTER,yyline,yychar,
                             yytext());}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.FINPARA,yyline,yychar,
                             yytext());}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.POTENCIA,yyline,yychar,
                             yytext());}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.INGRESAR,yyline,yychar,
                             yytext());}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.IMPRIMIR,yyline,yychar,
                             yytext());}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.CONVALOR,yyline,yychar,
                             yytext());}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.VERDADERO,yyline,yychar,
                             yytext());}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.FINSEGUN,yyline,yychar,
                             yytext());}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.HASTAQUE,yyline,yychar,
                             yytext());}
					case -60:
						break;
					case 60:
						{return new Symbol(sym.FINMETODO,yyline,yychar,
                             yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.FINFUNCION,yyline,yychar,
                             yytext());}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.IMPRIMIRNL,yyline,yychar,
                             yytext());}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.ESDIFERENTE,yyline,yychar,
                             yytext());}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.FINMIENTRAS,yyline,yychar,
                             yytext());}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.MENOIGUAL,yyline,yychar,
                             yytext());}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.MAYOIGUAL,yyline,yychar,
                             yytext());}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.CONPARAMETROS,yyline,yychar,
                             yytext());}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.DELOCONTRARIO,yyline,yychar,
                             yytext());}
					case -69:
						break;
					case 70:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -70:
						break;
					case 72:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -71:
						break;
					case 74:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -72:
						break;
					case 76:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -73:
						break;
					case 78:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -74:
						break;
					case 80:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -75:
						break;
					case 82:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -76:
						break;
					case 84:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -77:
						break;
					case 86:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -78:
						break;
					case 88:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -79:
						break;
					case 90:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -80:
						break;
					case 92:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -81:
						break;
					case 94:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -82:
						break;
					case 96:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -83:
						break;
					case 220:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -84:
						break;
					case 222:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -85:
						break;
					case 256:
						{
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}
					case -86:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
