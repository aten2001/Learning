package stringutil

import "testing"
import "fmt"

func TestReverse(t *testing.T) {
    cases := []struct {
        input, expected string
    }{
        {"Hello", "olleH"},
        {"", ""},
    }
    for _, c := range cases {
        output := Reverse(c.input)
        if output != c.expected {
            t.Errorf("Reverse(%q) == %q, expected %q",c.input,output,c.expected)
        }else{
            fmt.Println("All good")
        }
    }
}

