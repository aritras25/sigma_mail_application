package com.mail.main.Service;
/* Author: Aritra Saha */

import org.springframework.stereotype.Component;

@Component
public class MailTextBuilder {
    String mailText = "Dear Sir / Madam,\n" +
            "\n" +
            "We are in the field of management system consultancy since 1975 and served more than 200 customers providing various operation improvement solutions. \n" +
            "\n" +
            "COVID-19 pandemic has impacted every business - demand erosion, supply chain disruption, logistical issues, manpower availability, cash flow problem are to mention a few which challenges sustainability of the business. We feel this is the right time to review various business operations and working processes.\n" +
            "\n" +
            "While providing operation improvement solutions, we adopt Lean, Six Sigma, IS standards and other internationally accepted and proven principles and techniques. Following advantages can be leveraged from our services:\n\n" +
            "       1. Elimination of wastes / defects / rejection for decreasing cost of production and increasing customer satisfaction,\n" +
            "       2. Increasing productivity and efficiency,\n" +
            "       3. Optimizing raw materials inventory that improves cash-flow,\n" +
            "       4. Aligning production with customer demand also improves cash flow,\n" +
            "       5. Avoid  uncertainty resulting from complying applicable legal requirements,\n" +
            "       6. Branding your product / service improving quality and reliability which helps demand creation,\n" +
            "       7. Improve competency of employees, shop environment, work culture etc.\n\n" +
            "As management consultant we provide services on Management System Consulting, Industrial Engineering, System Improvement, Compliance, Audit & Advisory service and Corporate Training etc. Please find our corporate brochure for details.\n" +
            "\nIf you are interested we can discuss further details about our services. Please contact us:  Mobile: +91 96748 29885\n" +
            "Email ID:   info@sigmacpl.com; care@sigmacpl.com\n" +
            "\n" +
            "Thanks & Regards,\n" +
            "\n" +
            "Business Development Team,\n" +
            "Sigma Consultants Pvt. Ltd.\n" +
            "11, Camac Street, Kolkata – 700017\n" +
            "West Bengal, India Mobile: +91 96748 29885\n" +
            "Email ID:  info@sigmacpl.com; care@sigmacpl.com";

    String mailSubject = "Improve Operational Efficiency to Support Sustainability Goals";

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String subject) {
        this.mailSubject = subject;
    }
}
