#!/usr/bin/jjs -fv

var uri = "http://localhost:8080/doit/api/todos";
var command = "curl ${uri}";
print(command);
$EXEC(command);
var result = $OUT;
print(result);
var resultAsArray = JSON.parse(result);
print(resultAsArray);
for (todo in resultAsArray) {
    print(resultAsArray[todo].caption + "-" + resultAsArray[todo].description);
}

