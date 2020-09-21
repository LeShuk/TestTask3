INSERT INTO DOCTORS(name, patronymic, surname, specialization) VALUES
    ('Daniil', 'Markovich', 'Glihengauss', 'therapist'),
    ('Mark','Covidovich','Arsen`ev','pathologist'),
    ('Mihail','Anisovich','Googlenshtein','ophthalmologist'),
    ('Narzan','Alekseevich','Shults','cardiologist'),
    ('Eleonora','Akhmetova','Dunkan','anesthesiologist')
    ;

INSERT INTO PATIENTS(name, patronymic, surname, phone) VALUES
    ('Agafon','Kupriyanovich','Maksimov','505(229)087-63-878'),
    ('Naum','Rudolfovich','Belousov','03(875)930-11-497'),
    ('Martyn','Lukyanovich','Fedoseev','43(6347)244-01-341'),
    ('Mitrofan','Eldarovich','Makarov','09(613)151-64-818'),
    ('Lunara','Serapionovna','Kuznetsova','0(543)771-79-420'),
    ('Angela','Melorovna','Lobanova','2(48)883-49-988'),
    ('Rozalia','Igorevna','Sharapova','7(13)663-44-423'),
    ('Elvira','Eldarovna','Karpova','439(3455)487-48-529'),
    ('Marta','Kirillovna','Loginova','41(0834)784-17-947')
    ;

INSERT INTO RECIPES(patientid, doctorid, description, creationdate, validityindays, priority) VALUES
    ('3','0','"Adagio" - to stabilize the mood',DATE '2020-05-19','29','2'),
    ('6','2','"Avenue" - preparation for easy walking',DATE '2020-07-10','1','0'),
    ('8','4','"Anaconda" - cream-balm according to the recipes of oriental medicine to relieve muscle tension',
        DATE '2020-07-21','22','2'),
    ('4','4','"Vial" - removal of slight eye irritation',DATE '2020-07-22','26','2'),
    ('0','3','"Corsair" - lowering blood pressure',DATE '2020-08-10','11','1'),
    ('4','1','"Rapier" - to thin and remove phlegm from the lungs',DATE '2020-08-17','27','0'),
    ('5','1','"Freeway" - expansion of the bronchi',DATE '2020-09-01','13','1'),
    ('6','4','"Edem" - a drug for allergies',DATE '2020-09-02','10','2'),
    ('3','2','"Byte byte" - a protective cream against mosquitoes',DATE '2020-09-03','21','1'),
    ('3','2','"Vigilant" - additive for improving vision',DATE '2020-09-09','8','0'),
    ('6','1','"Equator" is a drug for the normalization of blood pressure',DATE '2020-09-10','3','2')
    ;












