package de.mirkoruether.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinqList<T> extends ArrayList<T>
{
    private static final long serialVersionUID = -8485556470489154672L;

    public LinqList()
    {
    }

    public LinqList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public LinqList(Collection<? extends T> c)
    {
        super(c);
    }

    @SafeVarargs
    public LinqList(T... arr)
    {
        super(Arrays.asList(arr));
    }

    public LinqList(Enumeration<? extends T> e)
    {
        super(Collections.list(e));
    }

    @Override
    public Object clone()
    {
        return super.clone();
    }

    public T first()
    {
        return get(0);
    }

    public T last()
    {
        return get(size() - 1);
    }

    public LinqList<T> where(Predicate<T> func)
    {
        LinqList<T> result = new LinqList<>();
        for(T obj : this)
        {
            if(func.test(obj))
            {
                result.add(obj);
            }
        }
        return result;
    }

    public <R> LinqList<R> select(Function<T, R> func)
    {
        LinqList<R> result = new LinqList<>(size());
        for(T obj : this)
        {
            result.add(func.apply(obj));
        }
        return result;
    }

    public boolean one(Predicate<T> func)
    {
        for(T obj : this)
        {
            if(func.test(obj))
                return true;
        }
        return false;
    }

    public boolean none(Predicate<T> func)
    {
        return !one(func);
    }

    public boolean all(Predicate<T> func)
    {
        for(T obj : this)
        {
            if(!func.test(obj))
                return false;
        }
        return true;
    }

    public int maxIndex(Function<T, Double> func)
    {
        double max = Double.NEGATIVE_INFINITY;
        int maxIndex = -1;
        for(int i = 0; i < size(); i++)
        {
            double val = func.apply(get(i));
            if(val > max)
            {
                maxIndex = i;
                max = val;
            }
        }

        return maxIndex;
    }

    public int minIndex(Function<T, Double> func)
    {
        return maxIndex(x -> -1.0 * func.apply(x));
    }

    public T maxObject(Function<T, Double> func)
    {
        return get(maxIndex(func));
    }

    public T minObject(Function<T, Double> func)
    {
        return get(minIndex(func));
    }

    public double maxValue(Function<T, Double> func)
    {
        return func.apply(maxObject(func));
    }

    public double minValue(Function<T, Double> func)
    {
        return func.apply(minObject(func));
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> clazz)
    {
        return toArray((T[])Array.newInstance(clazz, size()));
    }
}
