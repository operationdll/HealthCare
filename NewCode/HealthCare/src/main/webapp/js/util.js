//时间转换
function timestampToTime(timestamp) {
	if(timestamp==null||timestamp==''){
		return;
	}
	var date = new Date(timestamp);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() < 10 ? '0'+date.getDate() : date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes() + ':';
	s = date.getSeconds();
	return $.trim(Y+M+D);//return Y+M+D+h+m+s;
}

//复选框数据初始化
function initCheckBox(name,v){
	if(v===undefined||v==''){
		return;
	}
	let vs = v.split(',');
	for(let i = 0;i<vs.length;i++){
		if(vs[i]!=''){
			let CBs = $("input[name='"+name+"']");
			for(let j = 0;j<CBs.length;j++){
				if($(CBs[j]).val()==vs[i]){
					$(CBs[j]).attr( "checked","checked");
				}
			}
		}
	}
}

//获取复选框数据
function getCheckBox(name){
	let sel = $("input[name='"+name+"']:checked");
	let rstr = '';
	for(let i =0;i<sel.length;i++){
		rstr = rstr + $(sel[i]).val() + ',';
	}
	if(rstr!=''){
		rstr = rstr.substring(0,rstr.length-1);
	}
	return rstr;
}

//空值转换
function changeNullValue(v){
	if(v===undefined){
		v = '';
	}
	return v;
}

//单选框数据初始化
function initRedio(name,v){
	if(v===undefined||v==''){
		return;
	}
	let CBs = $("input[name='"+name+"']");
	for(let j = 0;j<CBs.length;j++){
		if($(CBs[j]).val()==v){
			$(CBs[j]).attr( "checked","checked");
		}
	}
}

//获取单选框数据
function getRedio(name){
	return $("input[name='"+name+"']:checked").val();
}

//验证小数
function validateFloat(val){
	var patten = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/;
	return patten.test(val);
}

//验证整数
function validateNum(val){
	var patten = /^-?\d+$/;
	return patten.test(val);
}

//小数点后两位百分比
function toPercent(num, total) { 
	if(num==0||total==0){
		return "0%";
	}
    return (Math.round(num / total * 10000) / 100.00 + "%");
}