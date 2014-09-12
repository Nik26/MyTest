var IMG = new Image();IMG.src = "img/img.jpg";

var canvasS;
var ctxS;

var canvasR;
var ctxR;

var W,H;


var a;
var aWidth;
var aHeight;

function waitForImages()//для проверки загрузки изображения
{
	if (!IMG.complete)  //если изоражение не загружено
	{
        setTimeout("waitForImages()", 100);
		return;
	}		
}

//инициализация 1го канваса
function initSource(){
	canvasS = document.getElementById("source");
	ctxS = canvasS.getContext('2d');

	ctxS.drawImage(IMG,0,0,canvasS.width,canvasS.height);

	W = canvasS.width;
	H = canvasS.height;
}

//инициализация 2го канваса
function initResult(){
	canvasR = document.getElementById("result");
	ctxR = canvasR.getContext("2d");

	ctxR.drawImage(IMG,0,0,canvasR.width,canvasR.height);
}

function preobraz(){
	a = ctxS.getImageData(0,0,W,H);
	aWidth = ctxR.getImageData(0,0,W,H);
	aHeight = ctxR.getImageData(0,0,W,H);
	var temp1;
	var temp2;


	for(var i=0;i<W;i++){
		var k = 0;
		for(var j=0;j<W*4;j+=8){//двигаеся на 2 пикселя

			temp1 = sum(a.data[j+i*W*4],a.data[j+i*W*4+4]);
			temp2 = razn(a.data[j+i*W*4],a.data[j+i*W*4+4]);

			aWidth.data[W*4*i+k] = temp1;
			aWidth.data[W*4*i+k+1] = temp1;
			aWidth.data[W*4*i+k+2] = temp1;

			aWidth.data[W*4*i+k+(W*2)] = temp2;
			aWidth.data[W*4*i+k+(W*2+1)] = temp2;
			aWidth.data[W*4*i+k+(W*2+2)] = temp2;

			k+=4;
		}	
	}

	for(var i=0;i<H*4;i+=4){
		var k = 0;
		for(var j=0; j<H; j+=2){
			temp1 = sum(aWidth.data[i+j*H*4],aWidth.data[i+j*H*4+H*4]);
			temp2 = razn(aWidth.data[i+j*H*4],aWidth.data[i+j*H*4+H*4]);

			aHeight.data[i+k*H*4] = temp1;
			aHeight.data[i+k*H*4+1] = temp1;
			aHeight.data[i+k*H*4+2] = temp1;

			aHeight.data[i+k*H*4+H*(H/2)*4] = temp2;
			aHeight.data[i+k*H*4+H*(H/2)*4+1] = temp2;
			aHeight.data[i+k*H*4+H*(H/2)*4+1] = temp2;

			k++;
		}
	}
	ctxR.putImageData(aHeight,0,0);
}

function sum(x1,x2){
	return parseInt((x1 + x2)/1.4);
}

function razn(x1,x2){
	return parseInt(Math.abs((x1-x2)/1.4));
}

function run(){
	waitForImages();
	initSource();
	
}

function Action(){
	initResult();
	preobraz();
}
