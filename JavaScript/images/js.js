var ctx,W,H;

var image_src = "image.jpg";

var IMG;IMG = new Image; IMG.src = image_src;

function main()
{
	
	waitForImages();
}

function waitForImages()
{
	for(var i = 0; i < IMG.lenght; i++)
	{
		if (!IMG.complete)
		{
			setTimeout("waitForImages()", 100);
			return;
		}
	}
	document.getElementById("wh").innerHTML = "w: " + IMG.width + " " + "h: " + IMG.height;
	initDraw();
}

function initDraw()
{
	var canvas = document.getElementById('canvas');
	W = canvas.width;
	H = canvas.height;
	ctx = canvas.getContext('2d');
	
	ctx.save();
		ctx.clearRect(0,0,W ,H);
		ctx.drawImage(IMG,0,0,800,451);
	ctx.restore();
}

function draw()
{
	ctx.save();
		ctx.clearRect(0,0,W ,H);
		ctx.drawImage(IMG,0,0,800,451);
	ctx.restore();
}

function clip1() {
	ctx.clearRect(0,0,W ,H);
	ctx.save();
		ctx.beginPath();
			ctx.arc(400,225,20,0,2*Math.PI,false);
			ctx.arc(400,225,80,0,2*Math.PI,true);
			ctx.clip();
			ctx.drawImage(IMG,0,0,800,451);
		ctx.closePath();
	ctx.restore();
}

function clip2() {
	ctx.clearRect(0,0,W,H);
	ctx.drawImage(IMG,0,0,800,451);
	ctx.fillStyle = "rgba(255,255,255,0.5)";
	ctx.fillRect(0,0,W,H);
	ctx.save();
	ctx.beginPath();
	ctx.arc(400,225,80,0,2*Math.PI,true);
	ctx.clip();
	ctx.drawImage(IMG,0,0,800,451);
	ctx.restore();
}

function clip3() {
	var s = 2;
	ctx.drawImage(IMG,0,0,800,451);
	ctx.strokeStyle = "white";
	ctx.lineWidth = 2;
	ctx.strokeRect(150,100,100,100);
	rotate(ctx);
}

function rotate(ctx) {
	ctx.save();
		ctx.strokeStyle = "black";
		ctx.translate(80,460);
		ctx.rotate(-Math.PI/8);
		ctx.translate(-80,-460);
		ctx.strokeRect(80,460,80,80);
		ctx.drawImage(IMG,150,100,100,100,80,460,80,80);
	ctx.restore();
}
