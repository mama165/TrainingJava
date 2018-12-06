package Ogury;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

//        LocalDate begin = LocalDate.of(2014, Month.APRIL, 7);
//        LocalDate end =  LocalDate.of(2014, Month.MAY,25);
//        long diffInDays = ChronoUnit.DAYS.between(begin, end);
//        long diffInMonths = ChronoUnit.MONTHS.between(begin, end);
//        long diffInYears = ChronoUnit.YEARS.between(begin, end);
//
//        System.out.println(diffInDays);


        solution(2014, "April", "May", "Wednesday");
    }

    public static int solution(int Y, String A, String B, String W) {
        // write your code in Java SE 8

        HashMap map = new HashMap();
        map.put("January", Month.JANUARY);
        map.put("February", Month.FEBRUARY);
        map.put("March", Month.MARCH);
        map.put("April", Month.APRIL);
        map.put("May", Month.MAY);
        map.put("June", Month.JUNE);
        map.put("July", Month.JULY);
        map.put("August", Month.AUGUST);
        map.put("September", Month.SEPTEMBER);
        map.put("October", Month.OCTOBER);
        map.put("November", Month.NOVEMBER);
        map.put("December", Month.DECEMBER);

        Enum first  = (Enum) map.get(A);
        Enum end = (Enum) map.get(B);

        LocalDate firstMondayOfMonth = LocalDate.of(Y, (Month) first, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        LocalDate lastDayOfMonth  = LocalDate.of(Y,(Month) end,1).with(TemporalAdjusters.lastDayOfMonth());

        LocalDate lastSundayOfMonth = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        long weeksBetweenDates = ChronoUnit.WEEKS.between(firstMondayOfMonth, lastSundayOfMonth.plusDays(1));

        return Math.toIntExact(weeksBetweenDates);
    }

    int solution(int M, int[] A) { // M > 1
        int N = A.length;   // A   longueur N
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)  // Good
            count[i] = 0; // Count = {0, 0, 0, 0...} taille M+1
        int maxOccurence = 1;
        int index = -1;
        for (int i = 0; i < N; i++) { // 0... N-1
            if (count[A[i]] > 0) {   // A[0] ... A[N-1]              count[A[0]] ... count[A[N-1]]  //Good
                int tmp = count[A[i]]; //Good
                if (tmp > maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp + 1;
            } else {
                count[A[i]] = 1;
            }
        }
        //
        if( index == -1) {
            return A[0];
        }

        //

        return A[index];
    }
}
