package academit;

import java.util.Locale;
import java.util.Scanner;
import java.lang.NullPointerException;

public class R {
    private double pointStart;
    private double pointEnd;

    private R(double pointStart, double pointEnd) {
        if (pointStart > pointEnd) {
            System.out.println("Значение конечной точки не должно быть меньше значения начальной!!!");
        } else {
            this.pointStart = pointStart;
            this.pointEnd = pointEnd;
        }
    }

    public double getPointStart() {
        return pointStart;
    }

    private void setPointStart(double pointStart) {
        this.pointStart = pointStart;
    }

    public double getPointEnd() {
        return pointEnd;
    }

    private void setPointEnd(double pointEnd) {
        this.pointEnd = pointEnd;
    }

    private static double getDistance(R range) {
        if (range == null) {
            throw new NullPointerException();
        }
        double difference = range.pointEnd - range.pointStart;
        System.out.printf("Длина отрезка прямой с точками %.2f  и  %.2f равна: %.2f %n", range.pointStart, range.pointEnd, difference);
        return difference;
    }

    private static void isInside(double pointChecked, R range) {
        if (range == null) {
            throw new NullPointerException();
        }
        if (pointChecked >= range.pointStart && pointChecked <= range.pointEnd) {
            System.out.printf("Точка %.2f принадлежит диапазону чисел %.2f и  %.2f %n%n", pointChecked, range.pointStart, range.pointEnd);
        } else {
            System.out.printf("Точка %.2f не принадлежит диапазону чисел %.2f и  %.2f %n%n", pointChecked, range.pointStart, range.pointEnd);
        }
    }

    private static void checkPointsRange(double pointStart, double pointEnd) {
        if (pointEnd < pointStart) {
            System.out.println("Значение конечной точки не должно быть меньше значения начальной!!! Введите значения снова!");
        }
    }

    private static R getIntersectionRanges(Range range1, Range range2) {
        R range3 = new R(0, 0);
        try {
            if (range1.pointEnd == range2.pointStart || range1.pointStart == range2.pointEnd) {
                range3.pointStart = Math.max(range1.pointStart, range2.pointStart);
                range3.pointEnd = Math.min(range1.pointEnd, range2.pointEnd);
                System.out.printf("Пересечением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: точка (%.2f)  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range3.pointStart);
                return range3;
            } else if (range1.pointStart == range2.pointStart && range1.pointEnd == range2.pointEnd) {
                System.out.printf("Пересечением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range1.pointStart, range1.pointEnd);
                return range1;
            } else if (Math.max(range1.pointStart, range2.pointStart) > Math.min(range1.pointEnd, range2.pointEnd)) {
                System.out.printf("Отрезок [%.2f;%.2f] с отрезком [%.2f;%.2f] не пересекаются. %n", range1.pointStart, range1.pointEnd, range2.pointStart, range2.pointEnd);
                return null;
            } else {
                range3.pointStart = Math.max(range1.pointStart, range2.pointStart);
                range3.pointEnd = Math.min(range1.pointEnd, range2.pointEnd);
                System.out.printf("Пересечением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range3.pointStart, range3.pointEnd);
            }
            return range3;
        } catch ( NullPointerException e ) {
            e.getMessage();
        }
        return range3;
    }

    private static R[] getUnionRanges(R range1, R range2) {
        R r1 = new R(0, 0);
        R r2 = new R(0, 0);
        R range[] = {r1, r2};
        try {
            if (range1.pointEnd == range2.pointStart || range1.pointStart == range2.pointEnd) {
                range[0].pointStart = (range1.pointStart > range2.pointStart) ? range2.pointStart : range1.pointStart;   //Math.min(range1.pointStart, range2.pointStart);
                range[0].pointEnd = (range1.pointEnd > range2.pointEnd) ? range1.pointEnd : range2.pointEnd;
                System.out.printf("Объединением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range[0].pointStart, range[0].pointEnd);
                return range;
            } else if (range1.pointStart == range2.pointStart && range1.pointEnd == range2.pointEnd) {
                range[0] = range1;
                System.out.printf("Объединением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range1.pointStart, range1.pointEnd);
                return range;
            } else if (Math.max(range1.pointStart, range2.pointStart) > Math.min(range1.pointEnd, range2.pointEnd)) {
                range[0] = range1;
                range[1] = range2;
                System.out.printf("Объединением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] являются отрезки [%.2f;%.2f] и [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range1.pointStart, range1.pointEnd, range2.pointStart, range2.pointEnd);
                return range;
            } else if (Math.max(range1.pointStart, range2.pointStart) < Math.min(range1.pointEnd, range2.pointEnd)) {
                range[0].pointStart = Math.min(range1.pointStart, range2.pointStart);
                range[0].pointEnd = Math.max(range1.pointEnd, range2.pointEnd);
                System.out.printf("Объединением отрезка [%.2f;%.2f] с отрезком [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range1.pointStart, range1.pointEnd,
                        range2.pointStart, range2.pointEnd, range[0].pointStart, range[0].pointEnd);
            }
            return range;
        } catch ( NullPointerException e ) {
            e.getMessage();
            return range;
        }
    }

    private static R[] getDifferenceRanges(R range1, R range2) {
        R r1 = new R(0, 0);
        R r2 = new R(0, 0);
        R range[] = {r1, r2};
        try {
            if (range1.pointEnd == range2.pointStart) {
                range[0].pointStart = range1.pointStart;
                range[0].pointEnd = range1.pointEnd;
                System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является: отрезок [%.2f;%.2f)  %n", range2.pointStart, range2.pointEnd,
                        range1.pointStart, range1.pointEnd, range[0].pointStart, range[0].pointEnd);
                return range;
            } else if (range2.pointEnd == range1.pointStart) {
                range[0].pointStart = range1.pointStart;
                range[0].pointEnd = range1.pointEnd;
                System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является: отрезок (%.2f;%.2f]  %n", range2.pointStart, range2.pointEnd,
                        range1.pointStart, range1.pointEnd, range[0].pointStart, range[0].pointEnd);
                return range;
            } else if (range1.pointStart == range2.pointStart && range1.pointEnd == range2.pointEnd) {
                System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является пустой отрезок %n", range2.pointStart, range2.pointEnd,
                        range2.pointStart, range2.pointEnd);
                return range;
            } else if (Math.max(range1.pointStart, range2.pointStart) > Math.min(range1.pointEnd, range2.pointEnd)) {
                range[0].pointStart = range1.pointStart;
                range[0].pointEnd = range1.pointEnd;
                System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является: отрезок [%.2f;%.2f]  %n", range2.pointStart, range2.pointEnd,
                        range1.pointStart, range1.pointEnd, range1.pointStart, range1.pointEnd);
                return range;
            } else if (Math.max(range1.pointStart, range2.pointStart) < Math.min(range1.pointEnd, range2.pointEnd)) {
                if (range1.pointStart < range2.pointStart) {
                    if (range1.pointEnd > range2.pointEnd) {
                        range[0].pointStart = range1.pointStart;
                        range[0].pointEnd = range2.pointStart;
                        range[1].pointStart = range2.pointEnd;
                        range[1].pointEnd = range1.pointEnd;
                        System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] являются отрезки [%.2f;%.2f) и (%.2f;%.2f]  %n",
                                range2.pointStart, range2.pointEnd, range1.pointStart, range1.pointEnd, range[0].pointStart, range[0].pointEnd, range[1].pointStart, range[1].pointEnd);
                    } else {
                        range[0].pointStart = range1.pointStart;
                        range[0].pointEnd = range2.pointStart;
                        System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является: отрезок [%.2f;%.2f)  %n", range2.pointStart, range2.pointEnd,
                                range1.pointStart, range1.pointEnd, range[0].pointStart, range[0].pointEnd);
                    }
                } else if (range1.pointEnd > range2.pointEnd) {
                    range[0].pointStart = range2.pointEnd;
                    range[0].pointEnd = range1.pointEnd;
                    System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является: отрезок (%.2f;%.2f]  %n", range2.pointStart, range2.pointEnd,
                            range1.pointStart, range1.pointEnd, range[0].pointStart, range[0].pointEnd);
                } else {
                    System.out.printf("Результатом вычитания отрезка [%.2f;%.2f] из отрезка [%.2f;%.2f] является пустой отрезок %n", range2.pointStart, range2.pointEnd,
                            range2.pointStart, range2.pointEnd);
                }
            }
            return range;
        } catch ( NullPointerException e ) {
            e.getMessage();
        }
        return range;
    }

    public static void main(String[] args) throws NullPointerException {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        R range1 = new R(0.0, 0.0);

        do {
            System.out.println("Введите значение начальной точки первого отрезка: ");
            range1.setPointStart(scanner.nextDouble());
            System.out.println("Введите значение конечной точки первого отрезка: ");
            range1.setPointEnd(scanner.nextDouble());

            checkPointsRange(range1.pointStart, range1.pointEnd);
        } while (range1.pointEnd < range1.pointStart);

        System.out.println("   Вычисление длины первого отрезка. ");
        getDistance(range1);

        System.out.println("   Проверка принадлежности точки первому отрезку.");
        System.out.println("Введите значение проверяемой точки: ");
        double pointChecked = scanner.nextDouble();

        isInside(pointChecked, range1);

        R range2 = new R(0.0, 0.0);
        do {
            System.out.println("Введите значение начальной точки второго отрезка: ");
            range2.setPointStart(scanner.nextDouble());
            System.out.println("Введите значение конечной точки второго отрезка: ");
            range2.setPointEnd(scanner.nextDouble());

            checkPointsRange(range2.pointStart, range2.pointEnd);

        } while (range2.pointEnd < range2.pointStart);

        System.out.println("   Вычисление длины второго отрезка прямой .");
        getDistance(range2);

        System.out.println("   Проверка принадлежности точки " + pointChecked + " второму отрезку.");

        isInside(pointChecked, range2);

        System.out.println("   Проверка принадлежности другой точки.");
        System.out.println("Введите новое значение проверяемой точки: ");
        pointChecked = scanner.nextDouble();

        isInside(pointChecked, range1);
        isInside(pointChecked, range2);

        System.out.println("   Получение пересечения, объединения и разности двух отрезков.");

        R r = getIntersectionRanges(range1, range2);
        R[] r1 = getUnionRanges(range1, range2);
        R[] r2 = getDifferenceRanges(range1, range2);
    }
}


