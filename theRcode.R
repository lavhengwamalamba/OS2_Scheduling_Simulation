hist(metric_times_1_with_500_patrons$`Waiting.Time`, main = "WhateverName you want", col = "thistle", xlab = "just type", ylab = "Another just type", xlim = c(0,250000))
hist(throughputs_1_with_500_patrons$`Count`, main = "Another Whatever", col = "goldenrod2", xlab = "blaaa", ylab = "olll")
#hist(throughputs_1_with_500_patrons$Count, breaks = seq(0, max(throughputs_1_with_500_patrons$Count), by = 10000), xlab = "Time Interval", ylab = "Count", main = "Histogram of Throughputs with 500 Patrons")
library(ggplot2)

#ggplot(throughputs_1_with_500_patrons, aes(x = throughputs_1_with_500_patrons$Time.Interval.milliseconds, y = throughputs_1_with_500_patrons$Count)) +
 # geom_bar(stat = "identity") +
  #labs(x = "Time Interval (milliseconds)", y = "Count") +
  #ggtitle("Histogram of Throughputs")
# Calculate the mean of the Count column
mean_count <- mean(throughputs_1_with_500_patrons$Count)

# Create the plot
ggplot(throughputs_1_with_500_patrons, aes(x = throughputs_1_with_500_patrons$Time.Interval.milliseconds, y = throughputs_1_with_500_patrons$Count)) +
  geom_bar(stat = "identity") +
  geom_hline(yintercept = mean_count, color = "red", linetype = "dashed") +
  labs(x = "Time Interval (milliseconds)", y = "Count", title = "Histogram of Throughputs with Overall Average Count") +
  annotate("text", x = Inf, y = mean_count, label = paste("Overall Average Count =", round(mean_count, 2)), hjust = -0.1, vjust = -1, color = "red")

# Calculate the mean of the Count column
mean_count <- mean(throughputs_1_with_500_patrons$Count)

# Create the histogram /////// this will actually give really good insight for me as well
hist(throughputs_1_with_500_patrons$Count,
     main = "Histogram of Throughputs with Average Count",
     xlab = "Count",
     ylab = "Frequency",
     col = "lightblue")

# Add a line for the mean
abline(v = mean_count, col = "red", lty = 2)
