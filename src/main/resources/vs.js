function getA(c, n){
	for (var d=!1,f="";!d;) {
		for (var f = "", k = 0; n > k; k++) var g = Math.floor(61 * Math.random()),
			f = f + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz".substring(g, g + 1);
		hstr = c + f;
		hashcash = qa(hstr);
		"00" == hashcash.substring(0, 2) && (d = !0)
	}
	return f;
}

function qa(a) {
	function c(a, c) {
		return a << c | a >>> 32 - c
	}
	function b(a) {
		var c = "",
			d, f;
		for (d = 7; 0 <= d; d--) f = a >>> 4 * d & 15, c += f.toString(16);
		return c
	}
	var f, e, g = Array(80),
		h = 1732584193,
		p = 4023233417,
		i = 2562383102,
		l = 271733878,
		m = 3285377520,
		o, n, t, q, r, a = function(a) {
			for (var a = a.replace(/\r\n/g, "\n"), c = "", b = 0; b < a.length; b++) {
				var d = a.charCodeAt(b);
				128 > d ? c += String.fromCharCode(d) : (127 < d && 2048 > d ? c += String.fromCharCode(d >> 6 | 192) : (c += String.fromCharCode(d >> 12 | 224), c += String.fromCharCode(d >> 6 & 63 | 128)), c += String.fromCharCode(d & 63 | 128))
			}
			return c
		}(a);
	o = a.length;
	var s = [];
	for (f = 0; f < o - 3; f += 4) e = a.charCodeAt(f) << 24 | a.charCodeAt(f + 1) << 16 | a.charCodeAt(f + 2) << 8 | a.charCodeAt(f + 3), s.push(e);
	switch (o % 4) {
	case 0:
		f = 2147483648;
		break;
	case 1:
		f = a.charCodeAt(o - 1) << 24 | 8388608;
		break;
	case 2:
		f = a.charCodeAt(o - 2) << 24 | a.charCodeAt(o - 1) << 16 | 32768;
		break;
	case 3:
		f = a.charCodeAt(o - 3) << 24 | a.charCodeAt(o - 2) << 16 | a.charCodeAt(o - 1) << 8 | 128
	}
	for (s.push(f); 14 != s.length % 16;) s.push(0);
	s.push(o >>> 29);
	s.push(o << 3 & 4294967295);
	for (a = 0; a < s.length; a += 16) {
		for (f = 0; 16 > f; f++) g[f] = s[a + f];
		for (f = 16; 79 >= f; f++) g[f] = c(g[f - 3] ^ g[f - 8] ^ g[f - 14] ^ g[f - 16], 1);
		e = h;
		o = p;
		n = i;
		t = l;
		q = m;
		for (f = 0; 19 >= f; f++) r = c(e, 5) + (o & n | ~o & t) + q + g[f] + 1518500249 & 4294967295, q = t, t = n, n = c(o, 30), o = e, e = r;
		for (f = 20; 39 >= f; f++) r = c(e, 5) + (o ^ n ^ t) + q + g[f] + 1859775393 & 4294967295, q = t, t = n, n = c(o, 30), o = e, e = r;
		for (f = 40; 59 >= f; f++) r = c(e, 5) + (o & n | o & t | n & t) + q + g[f] + 2400959708 & 4294967295, q = t, t = n, n = c(o, 30), o = e, e = r;
		for (f = 60; 79 >= f; f++) r = c(e, 5) + (o ^ n ^ t) + q + g[f] + 3395469782 & 4294967295, q = t, t = n, n = c(o, 30), o = e, e = r;
		h = h + e & 4294967295;
		p = p + o & 4294967295;
		i = i + n & 4294967295;
		l = l + t & 4294967295;
		m = m + q & 4294967295
	}
	r = b(h) + b(p) + b(i) + b(l) + b(m);
	return r.toLowerCase()
}

function encodeUrl(url){
	return encodeURIComponent(url);
}

function encodeUri(url){
	return escape(url);
}

function U8_16(_1) {
	var i, len, c;
	var char2, char3;
	var ary = [];
	len = _1.length;
	i = 0;
	while (i < len) {
		c = _1.charCodeAt(i++);
		switch (c >> 4) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			// 0xxxxxxx
			ary.push(_1.charAt(i - 1));
			break;
		case 12:
		case 13:
			// 110x xxxx   10xx xxxx
			char2 = _1.charCodeAt(i++);
			ary.push(String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F)));
			break;
		case 14:
			// 1110 xxxx 10xx xxxx 10xx xxxx
			char2 = _1.charCodeAt(i++);
			char3 = _1.charCodeAt(i++);
			ary.push(String.fromCharCode(((c & 0x0F) << 12) | ((char2 & 0x3F) << 6) | ((char3 & 0x3F) << 0)));
			break;
		}
	}
	return ary.join('');
}

function decode64(_1) {
	if(!_1) return '';
	var _2 = "ABCDEFGHIJKLMNOP"+"QRSTUVWXYZabcdef"+"ghijklmnopqrstuv"+"wxyz0123456789+/"+"=";
	var _3 = "";
	var _4, _5, _6;
	var _7, _8, _9, _a;
	var i = 0;
	_1 = _1.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	do {
		_7 = _2.indexOf(_1.charAt(i++));
		_8 = _2.indexOf(_1.charAt(i++));
		_9 = _2.indexOf(_1.charAt(i++));
		_a = _2.indexOf(_1.charAt(i++));
		_4 = (_7 << 2) | (_8 >> 4);
		_5 = ((_8 & 15) << 4) | (_9 >> 2);
		_6 = ((_9 & 3) << 6) | _a;
		_3 = _3 + String.fromCharCode(_4);
		if (_9 != 64) {
			_3 = _3 + String.fromCharCode(_5);
		}
		if (_a != 64) {
			_3 = _3 + String.fromCharCode(_6);
		}
	} while (i < _1.length);
	return U8_16(_3);
}

function encode64(str){
	if(!str) return '';
	str = str.toString();
	var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var base64DecodeChars = new Array(
       -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
       -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
       -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
       52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
       -1, 0,   1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
       15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
       -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
       41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
	);
	var out, i, len;
	var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
	while(i < len) {
		c1 = str.charCodeAt(i++) & 0xff;
		if(i == len)
		{
		    out += base64EncodeChars.charAt(c1 >> 2);
		    out += base64EncodeChars.charAt((c1 & 0x3) << 4);
		    out += "==";
		    break;
		}
		c2 = str.charCodeAt(i++);
		if(i == len)
		{
		    out += base64EncodeChars.charAt(c1 >> 2);
		    out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
		    out += base64EncodeChars.charAt((c2 & 0xF) << 2);
		    out += "=";
		    break;
		}
		c3 = str.charCodeAt(i++);
		out += base64EncodeChars.charAt(c1 >> 2);
		out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
		out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
		out += base64EncodeChars.charAt(c3 & 0x3F);
	}
	return out;
}

function encodeUid(uid){
	if(!uid) return '';
	var enUid = 'U' + encode64(uid << 2);
	return enUid;
}

function test(d){
	
	if((d = decode64(decodeURIComponent(d).split("|")[3]), -1 < d.indexOf(",") && -1 < d.indexOf("nn:"))){
		var e = d.split(",")[1].split(":")[1];
	}
	return d;
	
}