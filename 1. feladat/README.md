1. DiscountCalculator
    1. Hibás kódrészletek:

        orderValue > 10000 és orderValue <= 50000 (határértékek kezelése).

        discount += 5; (a százalék helyett egész számot ad hozzá).

        orderValue - discount (a fizetendő összegből a százalékot vonja le, nem a százalékkal csökkentett értéket számolja).

        Hiányzik a negatív értékek ellenőrzése.

    2. A hibák magyarázata:

        Pontosan 10 000 Ft esetén nem ad kedvezményt, pedig 10 000 Ft-tól már 5% járna. 50 000 Ft esetén 10%-ot ad 15% helyett.

        VIP kedvezménynél 0.05 helyett 5-öt ad hozzá, ami elrontja a teljes törtszám alapú százalékszámítást.

        A visszatérési értéknél a rendelési értékből levonja a törtszámot (pl. 10000 - 0.05 = 9999.95), nem pedig a levont összeget (orderValue * (1 - discount)).

        Negatív rendelési értékre nem dob kivételt vagy nem ad hibát.

    3. Javítás módja:

        JavaScript
        function calculateDiscountedPrice(orderValue, isVip) {
            if (orderValue < 0) {
                throw new Error("Negatív rendelési érték nem megengedett.");
            }

            let discount = 0;
            if (orderValue >= 50000) {
                discount = 0.15;
            } else if (orderValue >= 25000) {
                discount = 0.10;
            } else if (orderValue >= 10000) {
                discount = 0.05;
            }

            if (isVip) {
                discount += 0.05; // 5 százalékpont
            }

            if (discount > 0.20) {
                discount = 0.20;
            }

            return orderValue * (1 - discount);
        }



2. ParkingFeeCalculator
    1. Hibás kódrészletek:

        if (minutes < 15) (15 percnél nem ingyenes).

        Math.floor(minutes / 60) (minden megkezdett óra helyett lefelé kerekít).

        fee = fee - 20; (százalékos kedvezmény helyett fix 20 Ft-ot von le).

        Hiányzik a negatív idő ellenőrzése.

    2. A hibák magyarázata:

        15 perc esetén belép a díjköteles ágba, pedig az még ingyenes.

        Megkezdett órák számításához Math.ceil(minutes / 60) kell. A Math.floor(59 / 60) 0 órát adna megkezdett óra helyett.

        VIP kedvezménynél 20% helyett fix 20 Ft-ot von le.

        Negatív percekre nem dob hibát.

    3. Javítás módja:

        JavaScript
        function calculateParkingFee(minutes, isWeekend, isVip) {
            if (minutes < 0) {
                throw new Error("Negatív parkolási idő nem megengedett.");
            }

            if (minutes <= 15) {
                return 0;
            }

            let hours = Math.ceil(minutes / 60);
            let fee = hours * 600;

            if (isWeekend) {
                fee = fee * 0.5;
            }

            if (isVip) {
                fee = fee * 0.8; // 20% kedvezmény
            }

            if (fee > 5000) {
                fee = 5000;
            }

            return fee;
        }



3. CinemaTicketCalculator
    1. Hibás kódrészletek:

        age <= 6 és age < 18 (határértékek).

        Többszörös price = price * ... egymás utáni alkalmazása (halmozódó kedvezmények).

        price * 1.8 (3D felár százalékos, nem pedig fix 800 Ft).

        Hiányzik az életkor érvényességi ellenőrzése (age < 0 || age > 120).

    2. A hibák magyarázata:

        6 éves gyermek ingyenes, pedig a specifikáció szerint 6-tól már fizetős. 65 éves kor felettiek kedvezménye age >= 65 kellene legyen.

        A kód halmozza a kedvezményeket (pl. diák ÉS 65 év feletti esetén mindkettőt levonja), miközben csak a legnagyobb alkalmazható.

        A 3D felár fix 800 Ft a teljes összeghez, nem 80%-os áremelés. Ráadásul a 3D felárra nem vonatkozik kedvezmény.

    3. Javítás módja:

        JavaScript
        function calculateTicketPrice(age, isStudent, is3D) {
            if (age < 0 || age > 120) {
                throw new Error("Érvénytelen életkor.");
            }

            if (age < 6) {
                return is3D ? 800 : 0;
            }

            let maxDiscount = 0;

            if (age >= 6 && age <= 17) {
                maxDiscount = Math.max(maxDiscount, 0.30);
            }
            if (age >= 65) {
                maxDiscount = Math.max(maxDiscount, 0.40);
            }
            if (isStudent) {
                maxDiscount = Math.max(maxDiscount, 0.20);
            }

            let ticketPrice = 3000 * (1 - maxDiscount);

            if (is3D) {
                ticketPrice += 800; // Fix 800 Ft felár
            }

            return ticketPrice;
        }



4. ExamGradeCalculator
    1. Hibás kódrészletek:

        if (theory < 30 && practice < 20) (logikai ÉS az VAGY helyett).

        Pontszámintervallumok határai (total <= 60, total <= 70, total <= 85).

        Hiányzik a pontszámok érvényességi ellenőrzése (0–60 elmélet, 0–40 gyakorlat).

    2. A hibák magyarázata:

        Az && miatt csak akkor bukik meg elmélet/gyakorlat minimum hiányában, ha mindkettő elmarad tőle. Ha valaki elméletből 0 pontot ér el, de gyakorlatból 20-at, átengedné. Logikai || (VAGY) szükséges.

        A ponthatárok eltolódtak: 60 pontnál a táblázat szerint 3-as járna, de a kód 2-est ad (total <= 60). 70 pontnál 4-es járna, de a kód 3-ast ad.

        Nem ellenőrzi, hogy a pontszámok a megengedett [0, 60] és [0, 40] tartományba esnek-e.

    3. Javítás módja:

        JavaScript
        function calculateGrade(theory, practice) {
            if (theory < 0 || theory > 60 || practice < 0 || practice > 40) {
                throw new Error("Érvénytelen pontszám.");
            }

            if (theory < 30 || practice < 20) {
                return 1;
            }

            let total = theory + practice;

            if (total < 50) {
                return 1;
            } else if (total <= 59) {
                return 2;
            } else if (total <= 69) {
                return 3;
            } else if (total <= 84) {
                return 4;
            } else {
                return 5;
            }
        }



5. PackageClassifier
    1. Hibás kódrészletek:

        weight < 2 és méretek < 30 (szigorú kisebb mint operátorok használata).

        (length <= 60 || width <= 60 || height <= 60) (logikai VAGY az ÉS helyett).

        Hiányzik az érvénytelen csomag ellenőrzése (INVALID) a függvény elején.

    2. A hibák magyarázata:

        A legfeljebb 2 kg és legfeljebb 30 cm az megengedi a pontosan 2 kg-ot és 30 cm-t is (<=).

        A Medium méretnél a specifikáció szerint egyik méret sem lehet nagyobb 60 cm-nél, ami azt jelenti, hogy mindháromnak <= 60-nak kell lennie (&&). A kód ||-t használ, így ha csak az egyik méret 10 cm, de a többi 200 cm, akkor is Mediumnak sorolná be.

        Nem kezeli az 0 vagy negatív súlyt és méreteket (INVALID visszatérés).

    3. Javítás módja:

        JavaScript
        function classifyPackage(weight, length, width, height) {
            if (weight <= 0 || length <= 0 || width <= 0 || height <= 0) {
                return "INVALID";
            }

            // Small: <= 2kg ÉS mind3 <= 30cm
            if (weight <= 2 && length <= 30 && width <= 30 && height <= 30) {
                return "SMALL";
            }

            // Medium: <= 10kg ÉS mind3 <= 60cm
            if (weight <= 10 && length <= 60 && width <= 60 && height <= 60) {
                return "MEDIUM";
            }

            // Large: <= 30kg ÉS mind3 <= 120cm
            if (weight <= 30 && length <= 120 && width <= 120 && height <= 120) {
                return "LARGE";
            }

            return "OVERSIZE";
        }
