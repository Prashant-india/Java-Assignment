package com.shop;

public class Task4 {
    public class ReportDAO {

        private DataSource dataSource;

        public List<ReportEntry> fetchMonthlyReport(String accountId,
                                                    int month, int year)
                throws SQLException {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM report_entries " +
                            "WHERE account_id = ? AND MONTH(entry_date) = ? " +
                            "AND YEAR(entry_date) = ?"
            );
            ps.setString(1, accountId);
            ps.setInt(2, month);
            ps.setInt(3, year);

            List<ReportEntry> entries = new ArrayList<>();
            // FIX: Try-with-resources
            try(ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    entries.add(mapRow(rs));
                }
            }
            return entries;    // conn, ps, rs are never closed
        }
    }

}
