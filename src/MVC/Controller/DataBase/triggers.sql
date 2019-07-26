delimiter //

CREATE TRIGGER issn_add BEFORE INSERT ON livre
    FOR EACH ROW 
    BEGIN
        DECLARE done INT DEFAULT FALSE ;
        DECLARE done1 Int DEFAULT FALSE ;
        DECLARE issn INT;
        DECLARE issnNF INT DEFAULT 1;
        DECLARE issnNT INT DEFAULT 1;

        DECLARE curs1 CURSOR for SELECT issn FROM livre ORDER BY issn ASC ;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE ;

        OPEN curs1;

        read_loop : LOOP
                fetch curs1 into issn ;

                IF done THEN 
                    LEAVE read_loop;
                END IF ;

                IF issnNF < issn THEN 
                    SET issnNT = issnNF ; 
                    SET done1 = TRUE ;
                    LEAVE read_loop;
                ELSE 
                    SET issnNF = issn + 1;
                END IF;
        END LOOP ;

        CLOSE curs1;

        IF done1 THEN
            SET NEW.issn = issnNT ;
        ELSE 
            SET NEW.issn = issnNF ;
        END IF ;

END; //

delimiter ;


delimiter //

CREATE TRIGGER num_add BEFORE INSERT ON auteur
    FOR EACH ROW
BEGIN
    DECLARE done INT DEFAULT FALSE ;
    DECLARE done1 Int DEFAULT FALSE ;
    DECLARE num INT;
    DECLARE numNF INT DEFAULT 1;
    DECLARE numNT INT DEFAULT 1;

    DECLARE curs1 CURSOR for SELECT num FROM auteur ORDER BY num ASC ;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE ;

    OPEN curs1;

    read_loop : LOOP
        fetch curs1 into num ;

        IF done THEN
            LEAVE read_loop;
        END IF ;

        IF numNF < num THEN
            SET numNT = numNF ;
            SET done1 = TRUE ;
            LEAVE read_loop;
        ELSE
            SET numNF = num + 1;
        END IF;
    END LOOP ;

    CLOSE curs1;

    IF done1 THEN
        SET NEW.num = numNT ;
    ELSE
        SET NEW.num = numNF ;
    END IF ;

END; //

delimiter ;




        
            
