// init
var total_btn=4;
var select_page = 0;
var texts = new Array();

// Event ==============================================
window.addEventListener('load', function () {
  init();
});

document.querySelector('#memo').addEventListener('change', function () {
  var memo_data = document.querySelector('#memo').value;
  saveStorage(memo_data);
});

document.querySelector('.clear_button').addEventListener('click', function() {
  document.querySelector('#memo').value = "";
  clearMemo();
});

document.querySelector('#number_1').addEventListener('click', function() {
  getPage(0);
});

document.querySelector('#number_2').addEventListener('click', function() {
  getPage(1);
});

document.querySelector('#number_3').addEventListener('click', function() {
  getPage(2);
});

document.querySelector('#number_4').addEventListener('click', function() {
  getPage(3);
});


chrome.storage.sync.get( function (data) {
    var memo = document.querySelector("#memo");
    memo.value = data.memo;
    total_btn = data.total;
    }
);

// function ==============================================
function init() {
  getStorageData();
  getPage(0);
}

// clear
function clearMemo() {
  setTab("");
}

// get storage
function getStorageData() {
  chrome.storage.sync.get( function (data) {

    for (var i in data.memo) {
      texts.push(data.memo[i]);
    }
    total_btn = data.total;
    getPage(0)
    }
  );
}

function getPage(index) {
  this.select_page = index;
  var memo = document.querySelector("#memo");
  memo.value = this.texts[this.select_page];
}

//save storage
function saveStorage(memo) {
  texts[this.select_page] = memo;
  chrome.storage.sync.set({
    memo : this.texts,
    total : this.total_btn
  });
}

// document.querySelector('.plus_button').addEventListener('click', function() {
//   createTab();
// });

// document.querySelector('.minus_button').addEventListener('click', function() {
//   removeTab();
// });

// function createTab(){
//   var btn = document.createElement('button');
//   var number = total_btn + 1;
//   btn.setAttribute( "class", "number_button button");
//   btn.setAttribute( "id", "number_"+ number);  
//   btn.innerHTML = number;
//   document.getElementById('tab_container').appendChild(btn);
//   $('#tab_container').on('click',btn,function(){alert("test")});

//   total_btn = total_btn + 1;
//   setTab();
// }
// 
// function addTab(number){
//   console.log(this.total_btn);
//   console.log(number);
//   var btn = document.createElement('button');
//   btn.setAttribute( "class", "number_button button");
//   btn.setAttribute( "id", "number_"+ number);
//   btn.innerHTML = number;
//   document.getElementById('tab_container').appendChild(btn);
// }

// function removeTab(){
//   //alert("revmoe event");
//   var btn = document.getElementById("number_"+ this.total_btn);
//   // alert(btn.innerHTML);
//   if (this.total_btn === 1) {
//     return;
//   }
//   document.getElementById('tab_container').removeChild(btn);
//   this.total_btn = this.total_btn - 1;
//   setTab();
// }


