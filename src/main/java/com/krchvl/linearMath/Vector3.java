package com.krchvl.linearMath;

import java.util.Objects;

public final class Vector3 {
    public final double x;
    public final double y;
    public final double z;

    private static final double EPS = 1e-10;

    public Vector3(double x, double y, double z) {
        if (!Double.isFinite(x) || !Double.isFinite(y) || !Double.isFinite(z)) {
            throw new IllegalArgumentException("Координаты должны быть конечными числами");
        }

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 o) {
        Objects.requireNonNull(o, "Переданный в качестве аргумента вектор нулевой!");
        return new Vector3(x + o.x, y + o.y, z + o.z);
    }

    public Vector3 subtract(Vector3 o) {
        Objects.requireNonNull(o, "Переданный в качестве аргумента вектор нулевой!");
        return new Vector3(x - o.x, y - o.y, z - o.z);
    }

    public Vector3 scale(double s) {
        return new Vector3(x * s, y * s, z * s);
    }

    public Vector3 divide(double s) {
        if (Math.abs(s) < EPS) {
            throw new ArithmeticException("Деление на нулевой скаляр недопустимо!");
        }
        return new Vector3(x / s, y / s, z / s);
    }

    public double dot(Vector3 o) {
        Objects.requireNonNull(o, "Переданный в качестве аргумента вектор нулевой!");
        return x * o.x + y * o.y + z * o.z;
    }

    public Vector3 cross(Vector3 o) {
        Objects.requireNonNull(o, "Переданный в качестве аргумента вектор нулевой!");
        return new Vector3(
                y * o.z - z * o.y,
                z * o.x - x * o.z,
                x * o.y - y * o.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public Vector3 normalized() {
        double len = length();
        if (len < EPS) {
            throw new ArithmeticException("Невозможно привести нулевой вектор к нормализованному виду!");
        }
        return divide(len);
    }

    public boolean approxEquals(Vector3 o, double eps) {
        Objects.requireNonNull(o, "Переданный в качестве аргумента вектор нулевой!");
        return Math.abs(x - o.x) <= eps && Math.abs(y - o.y) <= eps && Math.abs(z - o.z) <= eps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3 v)) return false;
        return Double.doubleToLongBits(x) == Double.doubleToLongBits(v.x) &&
                Double.doubleToLongBits(y) == Double.doubleToLongBits(v.y) &&
                Double.doubleToLongBits(z) == Double.doubleToLongBits(v.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Vector3(" + x + ", " + y + ", " + z + ")";
    }
}