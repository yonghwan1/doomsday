// init
var total_btn=0;

function init() {
  if (total_btn === 0) {
    this.addTab();
  }
}

// Tab Event
function addTab(){
  var btn = document.createElement('button');
  var number = total_btn + 1;
  btn.setAttribute( "class", "number_button button");
  btn.setAttribute( "id", "number_"+ number);
  btn.innerHTML = number;
  document.getElementById('tab_container').appendChild(btn);
  total_btn = total_btn + 1;
}

function removeTab(){
  //alert("revmoe event");
  var btn = document.getElementById("number_"+ this.total_btn);
  // alert(btn.innerHTML);
  if (this.total_btn === 1) {
    return;
  }
  document.getElementById('tab_container').removeChild(btn);
  this.total_btn = this.total_btn - 1;
}


// Textarea Event

////크롬 스토리지에 저장된 값을 가져오세요. 
//chrome.storage.sync.get(function (data) {
//  // #user의 값으로 data의 값을 입력해주세요. 
//  document.querySelector('#user').value = data.userWords;
// 
//  //분석해서 그 결과를 #result에 넣어주세요. 
//  matching(data.userWords);
// 
//});
