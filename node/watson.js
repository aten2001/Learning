#!/usr/bin/env node

function processData(input){
  var lines = input.split("\n");
  var data = lines[0].split(" ");
  var N = +data[0];
  var K = +data[1];
  var Q = +data[2];
  Q += 2;
  var arry = lines[1].split(" ");
  for(var i=2; i<Q; i++){
    var indx = +lines[i];
    indx = (indx - K);
    indx = (indx < 0) ? N + indx : indx;
    console.log(arry[indx]);
  }

}


process.stdin.resume();

process.stdin.setEncoding('ascii');

_input = "";

process.stdin.on("data",function(input){
  _input += input;
});

process.stdin.on("end",function(){
  processData(_input);
});
