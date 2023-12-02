import java.text.DecimalFormat
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

class Triangle(val vertex1: Point, val vertex2: Point, val vertex3: Point) {

    fun inCircleCenter(): Point {
        val sideA = vertex2.distanceTo(vertex3)
        val sideB = vertex1.distanceTo(vertex3)
        val sideC = vertex1.distanceTo(vertex2)

        val totalPerimeter = sideA + sideB + sideC

        val centerX = (sideA * vertex1.x + sideB * vertex2.x + sideC * vertex3.x) / totalPerimeter
        val centerY = (sideA * vertex1.y + sideB * vertex2.y + sideC * vertex3.y) / totalPerimeter

        return Point(centerX, centerY)
    }


    fun inCircleRadius(center: Point): Double {
        val sideA = vertex2.distanceTo(vertex3)
        val sideB = vertex1.distanceTo(vertex3)
        val sideC = vertex1.distanceTo(vertex2)

        val semiPerimeter = (sideA + sideB + sideC) / 2

        val radius = sqrt((semiPerimeter - sideA) * (semiPerimeter - sideB) * (semiPerimeter - sideC) / semiPerimeter)

        return radius
    }
}

fun Point.distanceTo(other: Point): Double {
    val deltaX = this.x - other.x
    val deltaY = this.y - other.y
    return sqrt(deltaX * deltaX + deltaY * deltaY)
}

class Circle(val center: Point, val radius: Double)

fun main() {

    val vertex1 = Point(2.0, 1.0)
    val vertex2 = Point(3.0, 6.0)
    val vertex3 = Point(4.0, 5.0)

    val triangle = Triangle(vertex1, vertex2, vertex3)

    val inCircleCenter = triangle.inCircleCenter()
    val inCircleRadius = triangle.inCircleRadius(inCircleCenter)

    val inCircle = Circle(inCircleCenter, inCircleRadius)

    val df = DecimalFormat("#.###")

    println("Центр вписанной окружности: (${df.format(inCircle.center.x)}, ${df.format(inCircle.center.y)})")
    println("Радиус вписанной окружности: ${df.format(inCircle.radius)}")
}