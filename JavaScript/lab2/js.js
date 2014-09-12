var IMG = new Image(); IMG.src = "imgbw2.jpg";

var canvas;
var ctx;

var canvas2;
var ctx2;

var a; //массив для хранения данных о цветах в изображении

function main()
{
	waitForImages();
	initOririnal();
	initRezult();	
}

function waitForImages()//для проверки загрузки изображения
{
	if (!IMG.complete)  //если изоражение не загружено
	{
        setTimeout("waitForImages()", 100);
		return;
	}
		
}

function initOririnal() //инициализация исходного изображения
{
	canvas = document.getElementById("original");
	ctx = canvas.getContext('2d');

	ctx.drawImage(IMG,0,0,canvas.width,canvas.height);
}

function initRezult() // инициализация второго холста
{
	canvas2 = document.getElementById("result");
	ctx2 = canvas2.getContext('2d');
}

function linearSmooth() //линейный сглаживающий фильтр
{
	ctx2.clearRect(0,0,canvas2.width,canvas2.height); //очищаем второй холст
	a = ctx.getImageData(0,0,canvas.width,canvas.height);
	for(var i = 0; i < a.data.length; i++)
	{
		//красный
		a.data[i] = parseInt(( a.data[i-4] + a.data[i] + a.data[i+4] + a.data[i-(a.width*4)-4] + a.data[i-(a.width*4)] + a.data[i-(a.width*4)+4] + a.data[i+((a.width*4)-4)] + a.data[i+(a.width*4)] + a.data[i+((a.width*4)+4)])/9);
        //зеленый
        a1.data[i + 1] = a1.data[i];
        //синий
       a1.data[i + 2] = a1.data[i];
	}
    ctx2.putImageData(a, 0, 0); 
}

var c=1;
var bL = new Array(-1,-1,-1,    //фильтрующая матрица для лаплассиана
		  		   -1,8,-1,
		           -1,-1,-1);

function laplass()  //лаплассиан
{
	ctx2.clearRect(0,0,canvas2.width,canvas2.height); //очищаем второй холст
	a = ctx.getImageData(0,0,canvas.width,canvas.height);
	a1 = ctx.getImageData(0,0,canvas.width,canvas.height);
	for(var i = 0; i < a.data.length; i+=4)
	{
		//красный
		a1.data[i] = a.data[i-(500*4)-4]*bL[0] + a.data[i-(500*4)]*bL[1] + a.data[i-(500*4)+4]*bL[2]
						+ a.data[i-4]*bL[3] + a.data[i]*bL[4] + a.data[i+4]*bL[5] +  
						+ a.data[i+(500*4)-4]*bL[6] + a.data[i+(500*4)]*bL[7] + a.data[i+(500*4)+4]*bL[8];
        //зеленый
        a1.data[i + 1] = a1.data[i];
         //синий
        a1.data[i + 2] = a1.data[i];
	}
    ctx2.putImageData(a1, 0, 0);
}

var temp = new Array(); //массив для сортировки

function med() //медианный фильтр
{
	ctx2.clearRect(0,0,canvas2.width,canvas2.height); //очищаем второй холст
	a = ctx.getImageData(0,0,canvas.width,canvas.height);
	for(var i = 0; i < a.data.length; i++)
	{
		a.data[i] = medArrInit(i,0); 
		a.data[i + 1] = medArrInit(i,1);
		a.data[i + 2] = medArrInit(i,2);
	}
	ctx2.putImageData(a, 0, 0);
}

function medArrInit(index1,index2)//инициализация массива для сортировки
{
	temp[0] = a.data[(index1 -(a.width*4)-4)+index2]; //index1 - это i, index2 - это 0,1 или 2
	temp[1] = a.data[(index1 -(a.width*4))+index2];
	temp[2] = a.data[(index1 -(a.width*4)+4)+index2];
	temp[3] = a.data[(index1 - 4)+index2];
	temp[4] = a.data[index1 + index2];
	temp[5] = a.data[(index1 + index2)+4];
	temp[6] = a.data[(index1 + ((a.width*4)-4)) + index2];
	temp[7] = a.data[(index1 + (a.width*4)) + index2];
	temp[8] = a.data[(index1 + ((a.width*4)+4)) + index2];
	return sortMass(temp);
}

function sortMass(mass) //сортировка массива
{
	var size = mass.length; //размер массива
	var t;
	for(var i=0;i<size;i++)
	{
		for(var j = size - 1;j>i;--j)
		{
			if(mass[j] < mass[j-1])
			{
				t = mass[j-1];
				mass[j-1] = mass[j];
				mass[j] = t;
			}
		}
	}
	return mass[4]; //возврат пятого значения в массиве
}

function gradient() //градиент
{
	ctx2.clearRect(0,0,canvas2.width,canvas2.height);   //очищаем второй холст
	a = ctx.getImageData(0,0,canvas.width,canvas.height); //заносим данные о цветах изображения в массив
	a1 = ctx.getImageData(0,0,canvas.width,canvas.height);
	for(var i = 0; i < a.data.length; i+=4)
	{
		//красный
		a1.data[i] = Math.abs((a.data[i+((500*4)-4)] + 2*a.data[i+(500*4)] + a.data[i+((500*4)+4)] ) 
		- (a.data[i-(500*4)-4] + 2*a.data[i-(500*4)] + a.data[i-(500*4)+4])) 
		+ Math.abs((a.data[i-(500*4)+4] + 2*a.data[i+4] + a.data[i+(500*4)+4]) 
		- (a.data[i-500*4-4] + 2*a.data[i-4] + a.data[i+500*4-4]));
        //зеленый
        a1.data[i + 1] = a1.data[i];
         //синий
        a1.data[i + 2] = a1.data[i];
	}
    ctx2.putImageData(a1, 0, 0); //помещаем на холст новые значения цветов изображения
}