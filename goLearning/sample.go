
package main

import "fmt"

func main(){

    fmt.Println("Hello World");

    //var age int = 40
    var favNum float64 = 1.618

    randNum := 1        // shorthand for var randNum int = 1

    const pi float64 = 3.145

    var (
      varA = 2
      varB = 3
    )
    
    var myName string = "Gokul"

    var test bool = true

    fmt.Println(varA,varB)
    fmt.Println(favNum,randNum)
    fmt.Println(len(myName))
    fmt.Println(test)

    i := 1

    for i<= 10 {
        fmt.Println(i)
        i++
    }

    for j:= 0; j < 5; j++ {
      fmt.Println(j)
    }

    var1 := 6

    switch var1 {
        case 6: fmt.Println("six")
        default :fmt.Println("default")
    }


    // array

    var favNums[5] float64

    favNums[0] = 1
    fmt.Println(favNums[3])

    arr2 := [5]int { 1,2,3,4,5}

    for i, value := range arr2 {
        fmt.Println(i,value)
    }

    for _, value := range arr2 {
        fmt.Println(value)
    }

  // slice is like an array without size restriction
  numSlice := [] int { 4 ,5,7,8}
  slice2 := numSlice[2:3] // simlar to python can use :3 or 3:
  fmt.Println(slice2[0])
  // to init slice without values
  slice3 := make([]int,5,10) // init slice first 5 elements as 0 and max size of slice as 10
  copy(slice3, numSlice) // copy(dest,source)
  fmt.Println(slice3[0])

  slice3 = append(slice3, 0, -1) //append data to slce


  //Maps

  ageMap := make(map[string] int) // key, val
  ageMap["Name1"] = 42
  fmt.Println(ageMap["Name1"])
  fmt.Println(len(ageMap))
  ageMap["Name2"] = 42
  delete(ageMap,"Name2")
  sum := AddThemUp(slice3)
  fmt.Println("Sum:",sum)

  a, b := SampleFunc()
  fmt.Println("output of SampleFunc ",a,b)

  //variable input ot func

  fmt.Println(subtractThem(1,2,3,4,5,))

  newval := 3
  // sample closure
  doubleFunc := func () int {
      newval *= 2
      return newval
  }

  fmt.Println(doubleFunc())


  //defering functions
  defer printTwo() // runs this post termination of main
  printOne()

  fmt.Println(safeDiv(1,0))
  fmt.Println(safeDiv(91,3))
  demPanic()

 // pointers very similar to C
  x := 0

  changeVal(x) // pass by value
  fmt.Println(x)// does not change
  changeNow(&x) // pass by reference
  fmt.Println(x)// does  change

  //declaring new empy variable
  yPtr := new(int) // returns memory of int variable and pasess to yPtr
  changeNow(yPtr)
  fmt.Println(yPtr,*yPtr)

  //Struct way of defining our own datatype
  //To coninute form struct 31 min in the video
}

func changeVal(x int){
    x = 2
}

func changeNow(x *int) {
    *x = 2
}

func demPanic() {
    defer func() {
        fmt.Println(recover()) //similar to catch and print exception message
    }()

    panic("PANIC") // similar to throw exception
}
func safeDiv(num1, num2 int) int {
    defer func() {
        fmt.Println(recover()) // returns error msg from exception and supresses the error thrown prior to this execution. returns nil in case of no exception
    }()

    solution := num1/num2
    return solution
}
func printOne() {
    fmt.Println(1)
}
func printTwo() {
    fmt.Println(2)
}
func subtractThem(args ...int) int{ // similar to java for varargs
  finalValue :=0

  for _, val := range args {
      finalValue -=val
  }
  return finalValue
}

func AddThemUp(numbers []int) int {
    sum := 0
    for _, val := range(numbers){
        sum += val
    }
    return sum
}

func SampleFunc() (int, int) {
    return 1, 2
}
