function abc(a,b,c)
    print (a, b,c)
    return 3
end

function ret4()
    return 4, 5, 6, 7
end

function adder(x)
    return function(y)
        return x + y
    end
end
