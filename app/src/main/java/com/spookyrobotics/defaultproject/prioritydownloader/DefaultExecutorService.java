package com.spookyrobotics.defaultproject.prioritydownloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.spookyrobotics.defaultproject.prioritydownloader.downloader.DownloadThreadFactory;

public class DefaultExecutorService {
    /**
     * Extends default cached thread pool to spawn at max nThreads and cause new runnables to spawn
     * a new thread
     * @return
     */
    public static ExecutorService newCachedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(0, nThreads,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new DownloadThreadFactory());
    }

    /**
     * Extends default fixed thread pool to terminate threads after 30 seconds of idle and causes
     * new runnables to wait for a thread to service it
     * @param nThreads
     * @return
     */
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
}
