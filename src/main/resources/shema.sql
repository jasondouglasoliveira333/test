create table consulta (id integer not null, data_hora timestamp, data_hora_fim timestamp, especialidade_médica varchar(255), nome_paciente varchar(255), consultorio_id integer, medico_id integer, primary key (id));
create table consultorio (id integer not null, primary key (id));
create table medico (id integer not null, crm varchar(255), idade integer, nome varchar(255), primary key (id));
