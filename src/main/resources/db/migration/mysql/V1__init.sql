CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `gov_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `date_updated` TIMESTAMP NOT NULL,
  `date_created` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `government_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `profile` varchar(255) NOT NULL,
  `num_worked_hours_day` float DEFAULT NULL,
  `num_break_hours_day` float DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `hour_rate` decimal(19,2) DEFAULT NULL,
  `company_id` bigint(20) DEFAULT NULL,
  `date_updated` TIMESTAMP NOT NULL,
  `date_created` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `record` (
  `id` bigint(20) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `date_created` TIMESTAMP NOT NULL,
  `date_updated` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for table `company`
--
ALTER TABLE `company` ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee` ADD PRIMARY KEY (`id`), ADD KEY `employee_company_FK` (`company_id`);

--
-- Indexes for table `record`
--
ALTER TABLE `record` ADD PRIMARY KEY (`id`), ADD KEY `record_employee_FK` (`employee_id`);

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `record`
--
ALTER TABLE `record` MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee` ADD CONSTRAINT `employee_company_FK` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `record`
--
ALTER TABLE `record` ADD CONSTRAINT `record_employee_FK` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);