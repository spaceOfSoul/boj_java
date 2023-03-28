fun main(){
    var t : Int = readln().toInt()

    for (q in 1..t){
        var a:Int = readln().toInt()
        var maxDigit : Int = a/2
        var minDigit : Int = a/7
        var min_value : String = ""

        if(a%7 > 0)
            minDigit++;

        when(a%7){
            1->{
                min_value+="10"
                minDigit-=2;
            }
            2-> {
                min_value += "1"
                minDigit--;
            }
            3->{
                if(minDigit ==2){
                    minDigit-=2;
                    min_value+="22"
                }else if(minDigit>2){
                    minDigit-=3;
                    min_value+="200"
                }else {
                    min_value += "7"
                    minDigit--;
                }
            }
            4->{
                if(minDigit >1){
                    minDigit-=2;
                    min_value+="20"
                }else{
                    min_value+="4"
                    minDigit--;
                }
            }
            5-> {
                min_value += "2"
                minDigit--;
            }
            6-> {
                min_value += "6"
                minDigit--;
            }
        }

        for (i in 1..minDigit)
            min_value += 8.toString()

        print(min_value+" ")

        //min
        if(a%2 == 1)
            print(7)
        else
            print(1)
        for(i in 1 until maxDigit)
            print(1)
        println()
    }
}