var IMG = new Image(); IMG.src = "img.jpg"; //изображение

//канвас для изображения		
var canvas;
var ctx;

//канвас для черно-белого изображения
var canvas2;
var ctx2;

//red
var RedCanv;
var RedCtx;
var countRed = new Array;
for(var i =0;i<=255;i++)
{
    countRed[i] = 0;
}

//green
var GreenCanv;
var GreenCtx;
var countGreen = new Array;
for(var i =0;i<=255;i++)
{
    countGreen[i] = 0;
}

//blue
var BlueCanv;
var BlueCtx;
var countBlue = new Array;
for(var i =0;i<=255;i++)
{
    countBlue[i] = 0;
}

//black
var BlackCanv;
var BlackCtx;

//redBlack
var RedBCanv;
var RedBCtx;
var countBRed = new Array;
for(var i=0;i<=255;i++)
{
    countBRed[i] = 0;
}

//greenBlack
var GreenBCanv;
var GreenBCtx;
var countBGreen = new Array;
for(var i=0;i<=255;i++)
{
    countBGreen[i] = 0;
}

//blueBlack
var BlueBCanv;
var BlueBCtx;
var countBBlue = new Array;
for(var i=0;i<=255;i++)
{
    countBBlue[i] = 0;
}

var red = new Array;
var green = new Array;
var blue = new Array;

var redB = new Array;
var greenB = new Array;
var blueB = new Array;

var allColorC = 6540;
// общее колво пикселей это 400*327 = 130800 но у нас не будут видны графики 
//тогда я увеличил маштаб в 1500 раз сделал это так 
//130800 - 1 ноль = 13080 / 2 = 6540

function waitForImages()//для проверки загрузки изображения
{
	if (!IMG.complete)  //если изоражение не загружено
	{
        setTimeout("waitForImages()", 100);
		return;
	}
			
}

function main()
{
	canvas = document.getElementById("canv");
	ctx = canvas.getContext('2d');
    canvas2 = document.getElementById("canv2");
    ctx2 = canvas2.getContext('2d');

	waitForImages();
	ctx.drawImage(IMG,0,0,canvas.width,canvas.height);

    blackImage();
    
    getColorC();
    initRed();
    initGreen();
    initBlue();
}

function blackImage()
{
	var a = ctx.getImageData(0,0,canvas.width,canvas.height);
    for(var y = 0; y < a.height; y++)
    {
        for(var x = 0; x < a.width; x++)
        { 
            var i = (y * 4) * a.width + x * 4; 
            var avg = (a.data[i] + a.data[i + 1] + a.data[i + 2]) / 3; 
            a.data[i] = avg; 
            a.data[i + 1] = avg; 
            a.data[i + 2] = avg; 
        } 
    } 
    ctx2.putImageData(a, 0, 0, 0, 0, a.width, a.height); 
}

function initRed()
{
    var canvasR = document.getElementById("red");
    var ctxR = canvasR.getContext('2d');
    var canvasBR = document.getElementById("redB");
    var ctxBR = canvasBR.getContext('2d');

    drawOXOY(ctxR,canvasR);
    drawOXOY(ctxBR,canvasBR);
    drawGraph(ctxR,countRed,canvasR,"red");
    drawGraph(ctxBR,countBRed,canvasBR,"rgb(20,0,0)");
}

function initGreen()
{
    var canvasG = document.getElementById("green");
    var ctxG = canvasG.getContext('2d');
    var canvasBG = document.getElementById("greenB");
    var ctxBG = canvasBG.getContext('2d');
    
    drawOXOY(ctxG,canvasG);
    drawOXOY(ctxBG,canvasBG);
    drawGraph(ctxG,countGreen,canvasG,"green");
    drawGraph(ctxBG,countBGreen,canvasBG,"rgb(0,20,0)");
}

function initBlue()
{
    var canvasB = document.getElementById("blue");
    var ctxB = canvasB.getContext('2d');
    var canvasBB = document.getElementById("blueB");
    var ctxBB = canvasBB.getContext('2d');
    
    drawOXOY(ctxB,canvasB);
    drawOXOY(ctxBB,canvasBB);
    drawGraph(ctxB,countBlue,canvasB,"blue");
    drawGraph(ctxBB,countBBlue,canvasBB,"rgb(0,0,20)");
}

function drawGraph(context,ArrColor,canv,color){
	context.fillStyle = color;

    var x0 = parseInt(canv.width-((89/100)*canv.width));
    var y0 = parseInt(canv.height-((10/100)*canv.height));

    for(var i=0;i<ArrColor.length;i++){
        context.beginPath();
            context.fillRect(x0++, y0,1,-parseInt((procAllNum(ArrColor[i],allColorC)/100)*canv.height));
        context.closePath();
    }
}
    

function drawOXOY(context,canv)
{
     //ось Y
    context.beginPath();
        context.fillStyle = "black";
        context.moveTo(parseInt(canv.width-((90/100)*canv.width)),parseInt(canv.height-(90/100)*canv.height));
        context.lineTo(parseInt(canv.width-((90/100)*canv.width)),parseInt(canv.height-(10/100)*canv.height));
        context.stroke();
    context.closePath();
    //Ось Х
    context.beginPath();
        context.moveTo(parseInt(canv.width-((90/100)*canv.width)),parseInt(canv.height-(10/100)*canv.height));
        context.lineTo(parseInt(canv.width-((10/100)*canv.width)),parseInt(canv.height-(10/100)*canv.height));
        context.stroke();
    context.closePath();
}



function getColorC()
{
	var a = ctx.getImageData(0,0, canvas.width, canvas.height);
	var data = a.data;
    for(var i=0;i<data.length;i+=4)
	{   
        red[a.data[i]] = a.data[i];
        countRed[a.data[i]] = ++countRed[a.data[i]]; 
        green[a.data[i+1]] = a.data[i+1];
        countGreen[a.data[i+1]] = ++countGreen[a.data[i+1]];
        blue[a.data[i+2]]++;
        countBlue[a.data[i+2]] = ++countBlue[a.data[i+2]];
    }
	ctx.putImageData(a,0,0);

	
    var a = ctx2.getImageData(0,0, canvas2.width, canvas2.height);
    var data = a.data;
    for(var i=0;i<data.length;i+=4){
        redB[a.data[i]] = a.data[i];
        countBRed[a.data[i]] = ++countBRed[a.data[i]]; 
        greenB[a.data[i+1]] = a.data[i+1];
        countBGreen[a.data[i+1]] = ++countBGreen[a.data[i+1]];
        blueB[a.data[i+2]]++;
        countBBlue[a.data[i+2]] = ++countBBlue[a.data[i+2]];
    }
    ctx2.putImageData(a,0,0);
}

function procAllNum(color,all)
{
    return parseInt((100*color)/all);
}
