CREATE TABLE employee (
                          id              SERIAL PRIMARY KEY,
                          name            VARCHAR(20),
                          joined_date     DATE,
                          department_name VARCHAR(20),
                          email           VARCHAR(64),
                          birth_day       DATE
);
