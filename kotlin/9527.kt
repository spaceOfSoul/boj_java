import kotlin.math.pow

var array = LongArray(0)
fun getMsb(num: Long): Int { // msb 위치 반환
    var n = num
    var m = 0
    while (n > 1) {
        n = (n shr 1)
        m++
    }
    return m
}

fun getNextMsb(num: Long, m: Int): Int {
    var i = m - 1
    while (i >= 0) {
        if (num and (1L shl i) != 0L)
            return i
        i--
    }
    return -1 // m 이하에 1이 없는 경우 -1 반환
}

fun findAllBit(n: Long): Long {
    var sum : Long = n and 1L; // 맨 우측 LSB set 여부
    var num =n;
    var most = 63;
    most = getNextMsb(num,most)
    while(most>0){
        sum += array[most-1]+(num-(1L shl most)+1)
        num -= (1L shl most) // 맨 좌측 비트를 없앰.
        most = getNextMsb(num,most)
    }
//    println(sum)
    return sum;
}


fun main() {
    var A : Long
    var B : Long
    val tmp = readln().split(" ").map { it.toLong() }
    A = tmp.first().toLong()
    B = tmp.last().toLong()

    var sum : Long = 0
    array+=1
    for(i in 1..getMsb(B))
        array+=(array[i.toInt()-1])*2 + (1L shl i)
    
    println(findAllBit(B)- findAllBit(A-1))
}