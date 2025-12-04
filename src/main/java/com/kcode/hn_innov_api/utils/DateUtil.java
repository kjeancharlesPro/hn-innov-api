package com.kcode.hn_innov_api.utils;

import com.kcode.hn_innov_api.entity.PeriodEntity;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class DateUtil {
    public static void prepareNextDate(PeriodEntity periodEntity){
        LocalDateTime today = LocalDateTime.now();

        // Aller au lundi de la semaine prochaine
        LocalDateTime nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        // À partir de ce lundi, calculer mercredi et vendredi
        LocalDateTime nextWednesday = nextMonday.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
        LocalDateTime nextFriday = nextMonday.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        // Ajouter l'heure 15h00
        LocalDateTime wednesdayAt3pm = nextWednesday.toLocalDate().atTime(15, 0);
        LocalDateTime fridayAt3pm = nextFriday.toLocalDate().atTime(15, 0);

        periodEntity.setStartDate(wednesdayAt3pm);
        periodEntity.setEndDate(fridayAt3pm);
    }
}
